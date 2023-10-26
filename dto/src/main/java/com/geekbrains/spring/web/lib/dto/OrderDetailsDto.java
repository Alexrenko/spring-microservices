package com.geekbrains.spring.web.lib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto implements Serializable {

    @Schema(description = "Адрес", example = "Nevskyi avenue 33 ap.44")
    private String address;

    @Schema(description = "Телефон", example = "89223334567")
    private String phone;
}
