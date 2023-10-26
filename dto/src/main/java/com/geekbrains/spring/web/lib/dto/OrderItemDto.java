package com.geekbrains.spring.web.lib.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    @Schema(description = "id товара", example = "1")
    private long productId;
    @Schema(description = "Описание товара", example = "Хлеб")
    private String title;
    @Schema(description = "Количество", example = "5")
    private int quantity;
    @Schema(description = "Цена одного товара", example = "100")
    private int pricePerProduct;
    @Schema(description = "Общая цена", example = "500")
    private int price;

    public OrderItemDto(long productId, String title, int quantity,
                        int pricePerProduct, int price) {
        this.productId = productId;
        this.title = title;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public OrderItemDto(ProductDto productDto){
        this.productId = productDto.getId();
        this.title = productDto.getTitle();
        this.quantity = 1;
        this.pricePerProduct = productDto.getPrice();
        this.price = productDto.getPrice();
    }

    public OrderItemDto(ProductDto productDto, int quantity) {
        this(productDto);
        this.quantity = quantity;
    }

    public void changeQuantity(int delta){
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

}
