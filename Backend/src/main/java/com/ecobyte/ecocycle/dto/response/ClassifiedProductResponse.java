package com.ecobyte.ecocycle.dto.response;

import com.ecobyte.ecocycle.domain.product.RecyclingProduct;
import lombok.Getter;

@Getter
public class ClassifiedProductResponse {

    private final Long id;
    private final String name;
    private final String recyclingInfo;
    private final String tip;
    private final String imageUrl;

    public ClassifiedProductResponse(final Long id, final String name, final String recyclingInfo, final String tip,
                                     final String imageUrl) {
        this.id = id;
        this.name = name;
        this.recyclingInfo = recyclingInfo;
        this.tip = tip;
        this.imageUrl = imageUrl;
    }

    public static ClassifiedProductResponse of(final RecyclingProduct recyclingProduct, final String imageUrl) {
        return new ClassifiedProductResponse(recyclingProduct.getId(),
                recyclingProduct.getName(),
                recyclingProduct.getRecyclingInfo(),
                recyclingProduct.getTip(),
                imageUrl);
    }
}
