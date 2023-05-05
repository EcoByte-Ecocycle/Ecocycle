package com.ecobyte.ecocycle.dto.response;

import com.ecobyte.ecocycle.domain.product.RecyclingProduct;
import lombok.Getter;

@Getter
public class RecyclingProductResponse {

    private final Long id;
    private final String name;
    private final String recyclingInfo;
    private final String tip;

    public RecyclingProductResponse(final Long id, final String name, final String recyclingInfo, final String tip) {
        this.id = id;
        this.name = name;
        this.recyclingInfo = recyclingInfo;
        this.tip = tip;
    }

    public static RecyclingProductResponse from(final RecyclingProduct recyclingProduct) {
        return new RecyclingProductResponse(recyclingProduct.getId(),
                recyclingProduct.getName(),
                recyclingProduct.getRecyclingInfo(),
                recyclingProduct.getTip());
    }
}
