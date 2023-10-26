package com.geekbrains.spring.web.products.controllers;


import com.geekbrains.spring.web.lib.dto.ProductDto;
import com.geekbrains.spring.web.products.converters.ProductConverter;

import com.geekbrains.spring.web.products.entities.Product;
import com.geekbrains.spring.web.products.converters.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.products.services.ProductService;
import com.geekbrains.spring.web.products.validators.ProductValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "ProductController", description = "API по работе с товарами")
public class ProductsController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    @Operation(summary = "Возвращает все товары из базы данных")
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, page).map (
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Возвращает товар по id")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    @Operation(summary = "Добавляет новый товар в базу данных")
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    @Operation(summary = "Обновляет товар в базе данных")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаляет товар из базы данных")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
