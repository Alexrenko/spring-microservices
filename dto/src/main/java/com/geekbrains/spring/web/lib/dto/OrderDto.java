package com.geekbrains.spring.web.lib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @Schema(description = "id товара", example = "2")
    private long id;

    @Schema(description = "Имя пользователя", example = "bob")
    private String username;

    @Schema(description = "Список OrderItemDto")
    private List<OrderItemDto> items;

    @Schema(description = "Общая цена", example = "1200")
    private int totalPrice;

    @Schema(description = "Адрес", example = "Невский проспект 55, кв. 77")
    private String address;

    @Schema(description = "Телефон", example = "89229345566")
    private String phone;
}
