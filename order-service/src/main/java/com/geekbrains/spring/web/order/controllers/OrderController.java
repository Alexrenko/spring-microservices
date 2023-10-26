package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.lib.dto.OrderDto;
import com.geekbrains.spring.web.order.converters.OrderConverter;
import com.geekbrains.spring.web.order.services.OrderService;
import com.geekbrains.spring.web.order.services.QiwiPayFormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "OrderController", description = "Контроллер для работы с заказами")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderConverter orderConverter;

    @GetMapping
    @Operation(summary = "Возвращает текущий заказ по имени пользователя")
    public List<OrderDto> getCurrenOrders(@RequestHeader String username){
        return orderService.findOrderByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @PostMapping("/test")
    public String test(@RequestBody String cartName) {
        System.out.println("!!!!!!!!!!! " + cartName + " !!!!!!!!!!!!!!");
        return "OK";
    }


}
