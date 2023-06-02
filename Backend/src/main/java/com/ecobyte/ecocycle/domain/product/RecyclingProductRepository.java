package com.ecobyte.ecocycle.domain.product;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface RecyclingProductRepository extends Repository<RecyclingProduct, Long> {

    RecyclingProduct save(final RecyclingProduct product);

    Optional<RecyclingProduct> findByName(final String name);
}
