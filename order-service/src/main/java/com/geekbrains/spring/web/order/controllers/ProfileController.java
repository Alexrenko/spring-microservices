package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.lib.dto.ProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "ProfileController", description = "Контроллер для работы с профилем")
public class ProfileController {
    @GetMapping
    @Operation(summary = "Возвращает новый профиль пользователя")
    public ProfileDto getCurrentUserInfo(@RequestHeader String username) {
        return new ProfileDto(username);
    }
}
