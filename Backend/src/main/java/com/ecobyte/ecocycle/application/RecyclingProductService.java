package com.ecobyte.ecocycle.application;

import com.ecobyte.ecocycle.domain.product.RecyclingProduct;
import com.ecobyte.ecocycle.domain.product.RecyclingProductRepository;
import com.ecobyte.ecocycle.domain.user.User;
import com.ecobyte.ecocycle.domain.user.UserRepository;
import com.ecobyte.ecocycle.dto.request.RecyclingProductRequest;
import com.ecobyte.ecocycle.dto.response.RecyclingProductResponse;
import com.ecobyte.ecocycle.exception.NoDataException;
import com.ecobyte.ecocycle.exception.UserNotFoundException;
import com.ecobyte.ecocycle.support.DataClassificationClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RecyclingProductService {

    private final UserRepository userRepository;
    private final RecyclingProductRepository recyclingProductRepository;
    private final DataClassificationClient dataClassificationClient;

    public RecyclingProductService(final UserRepository userRepository,
                                   final RecyclingProductRepository recyclingProductRepository,
                                   final DataClassificationClient dataClassificationClient) {
        this.userRepository = userRepository;
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

    public RecyclingProductResponse classify(final Long loginId, final String imageUrl) {
        final User user = userRepository.findById(loginId).orElseThrow(UserNotFoundException::new);
        final String productName = dataClassificationClient.classifyProduct(imageUrl);
        final RecyclingProduct recyclingProduct = recyclingProductRepository.findByName(productName)
                .orElseThrow(NoDataException::new);

        user.addStamps(10);
        return RecyclingProductResponse.from(recyclingProduct);
    }
}
