package com.ecobyte.ecocycle.application;

import com.ecobyte.ecocycle.domain.product.ClassifiedData;
import com.ecobyte.ecocycle.domain.product.RecyclingProduct;
import com.ecobyte.ecocycle.domain.product.RecyclingProductRepository;
import com.ecobyte.ecocycle.dto.request.RecyclingProductRequest;
import com.ecobyte.ecocycle.dto.response.ClassifiedProductResponse;
import com.ecobyte.ecocycle.dto.response.ClassifiedProductsResponse;
import com.ecobyte.ecocycle.dto.response.RecyclingProductResponse;
import com.ecobyte.ecocycle.exception.NoDataException;
import com.ecobyte.ecocycle.support.DataClassificationClient;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RecyclingProductService {

    private final RecyclingProductRepository recyclingProductRepository;
    private final DataClassificationClient dataClassificationClient;

    public RecyclingProductService(final RecyclingProductRepository recyclingProductRepository,
                                   final DataClassificationClient dataClassificationClient) {
        this.recyclingProductRepository = recyclingProductRepository;
        this.dataClassificationClient = dataClassificationClient;
    }

    @Transactional
    public RecyclingProductResponse save(final RecyclingProductRequest request) {
        final RecyclingProduct recyclingProduct = new RecyclingProduct(request.getName(), request.getRecyclingInfo(),
                request.getTip());

        final RecyclingProduct savedRecyclingProduct = recyclingProductRepository.save(recyclingProduct);
        return RecyclingProductResponse.from(savedRecyclingProduct);
    }

    public ClassifiedProductsResponse classify(final String imageUrl) {
        final List<ClassifiedData> classifiedDatas = dataClassificationClient.classifyProduct(imageUrl);
        final List<ClassifiedProductResponse> classifiedProductsResponses = classifiedDatas.stream()
                .map(this::getClassifiedProductResponse)
                .collect(Collectors.toList());
        return new ClassifiedProductsResponse(classifiedProductsResponses);
    }

    private ClassifiedProductResponse getClassifiedProductResponse(final ClassifiedData data) {
        final RecyclingProduct recyclingProduct = recyclingProductRepository
                .findByName(data.getProductName())
                .orElseThrow(NoDataException::new);

        return ClassifiedProductResponse.of(recyclingProduct, data.getImageUrl());
    }
}
