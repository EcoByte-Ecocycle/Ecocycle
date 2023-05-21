package com.ecobyte.ecocycle.support;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleanUp {

    @PersistenceContext
    private EntityManager entityManager;

    private List<TableTuple> tableTuples;

    @PostConstruct
    public void init() {
        this.tableTuples = entityManager.getMetamodel().getEntities().stream()
                .map(EntityType::getJavaType)
                .map(TableTuple::of)
                .filter(TableTuple::isAutoGeneratedId)
                .collect(Collectors.toList());
    }

    @Transactional
    public void execute() {
        entityManager.flush();

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (TableTuple tuple : tableTuples) {
            entityManager
                    .createNativeQuery("TRUNCATE TABLE " + tuple.name).executeUpdate();
            entityManager
                    .createNativeQuery("ALTER TABLE " + tuple.name + " ALTER COLUMN " + tuple.id + " RESTART WITH 1")
                    .executeUpdate();
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

    private static class TableTuple {
        public String name;
        public String id;

        public TableTuple(final String name, final String id) {
            this.name = name;
            this.id = id;
        }

        public static TableTuple of(Class<?> entityType) {
            return new TableTuple(tableName(entityType), id(entityType));
        }

        private static String tableName(final Class<?> entityType) {
            return entityType.getAnnotation(Table.class).name();
        }

        private static String id(final Class<?> entityType) {
            return Arrays.stream(entityType.getDeclaredFields())
                    .filter(field -> field.getAnnotation(Id.class) != null)
                    .filter(field -> field.getAnnotation(GeneratedValue.class) != null)
                    .map(Field::getName)
                    .findAny()
                    .orElse(Strings.EMPTY);
        }

        public static boolean isAutoGeneratedId(TableTuple tableTuple) {
            return !tableTuple.id.equals(Strings.EMPTY);
        }
    }
}
