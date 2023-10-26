package com.geekbrains.spring.web.cart.controllers;


import com.geekbrains.spring.web.cart.api.ProductApi;
import com.geekbrains.spring.web.cart.dto.Cart;
import com.geekbrains.spring.web.cart.service.CartService;
import com.geekbrains.spring.web.lib.dto.OrderDetailsDto;
import com.geekbrains.spring.web.lib.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Tag(name = "CartController", description = "API по работе с корзиной товаров")
public class CartController {
    private final CartService cartService;

    @Autowired
    private ProductApi productApi;

    @PostMapping
    @Operation(summary = "Возвращает текущую корзину")
    public Cart getCurrentCart(@RequestBody String cartName){
        return cartService.getCurrentCart(cartName);
    }

    @PostMapping("/add/{id}")
    @Operation(summary = "Добавляет товар в корзину по id")
    public void addProductToCart(@PathVariable Long id,
                                 @RequestBody String cartName,
                                 @RequestParam (name = "q", defaultValue = "1") int quantity) {
        cartService.addProductByIdToCart(id, cartName, quantity);
    }

    @PostMapping("/decrease/{id}")
    @Operation(summary = "Уменьшает количество товара в корзине. ")
    public void decreaseProductFromCart(@PathVariable Long id,
                                        @RequestBody String cartName,
                                        @RequestParam (name = "d", defaultValue = "1") int delta) {
        cartService.decreaseProduct(id, cartName, delta);
    }

    @PostMapping("/remove/{id}")
    @Operation(summary = "Удаляет товар из корзины")
    public void removeProductFromCart(@PathVariable Long id,
                                      @RequestBody String cartName) {
        cartService.removeProduct(id, cartName);
    }

    @PostMapping("/clear")
    @Operation(summary = "Очищает корзину")
    public void clearCart(@RequestBody String cartName) {
        cartService.clear(cartName);
    }

    @PostMapping("/createOrder")
    @Operation(summary = "Создает новый заказ")
    public void createOrder(@RequestHeader String username,
                            @RequestBody Map<String, String> map) {
        cartService.createOrder(
                username,
                new OrderDetailsDto(map.get("address"), map.get("phone")),
                map.get("cartName")
        );
    }

    @GetMapping("/testFeign")
    @Operation(summary = "Возвращает все товары в корзине")
    public Page<ProductDto> getProductPages() {
        return productApi.getAllProducts();
    }

}
