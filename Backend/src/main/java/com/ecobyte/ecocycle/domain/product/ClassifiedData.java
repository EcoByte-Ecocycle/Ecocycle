package com.ecobyte.ecocycle.domain.product;

import lombok.Getter;

@Getter
public class ClassifiedData {

    private final String productName;
    private final String imageUrl;

    public ClassifiedData(final String productName, final String imageUrl) {
        this.productName = productName;
        this.imageUrl = imageUrl;
    }
}
