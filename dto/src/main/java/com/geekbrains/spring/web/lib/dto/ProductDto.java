package com.geekbrains.spring.web.lib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Schema(description = "id товара", example = "2")
    private long id;

    @Schema(description = "Описание товара", example = "Монитор")
    private String title;

    @Schema(description = "Цена", example = "100")
    private int price;
}
