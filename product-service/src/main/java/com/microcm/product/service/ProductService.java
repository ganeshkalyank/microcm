package com.microcm.product.service;

import com.microcm.product.dto.ProductRequest;
import com.microcm.product.dto.ProductResponse;
import com.microcm.product.model.Product;
import com.microcm.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder().name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product {} is saved.", product.getId());

    }

    public List<ProductResponse> getAllProducts(){
        List<Product> prods = productRepository.findAll();
        return prods.stream().map(this::prodToProdResponse).toList();
    }

    private ProductResponse prodToProdResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
