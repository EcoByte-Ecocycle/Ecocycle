package com.ecobyte.ecocycle.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RecyclingProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String recyclingInfo;

    @Column(nullable = false)
    private String tip;

    public RecyclingProduct(final String name, final String recyclingInfo, final String tip) {
        this(null, name, recyclingInfo, tip);
    }

    public RecyclingProduct(final Long id, final String name, final String recyclingInfo, final String tip) {
        this.id = id;
        this.name = name;
        this.recyclingInfo = recyclingInfo;
        this.tip = tip;
    }
}
