package com.geekbrains.spring.web.products.validators;


import com.geekbrains.spring.web.products.converters.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import com.geekbrains.spring.web.lib.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice() < 1) {
            errors.add("Цена продукта не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
