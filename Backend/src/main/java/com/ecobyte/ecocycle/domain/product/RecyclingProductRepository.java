package com.ecobyte.ecocycle.domain.product;

import org.springframework.data.repository.Repository;

public interface RecyclingProductRepository extends Repository<RecyclingProduct, Long> {

    RecyclingProduct save(final RecyclingProduct product);
}
