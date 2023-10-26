package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.order.services.QiwiPayFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/qiwi")
public class QiwiController {

    @Autowired
    private QiwiPayFormService qiwiPayFormService;

    @PostMapping("/payform")
    public String createPayForm(@RequestBody String cartName) {
        System.out.println("createPayForm - QiwiController2222222222222");
        return qiwiPayFormService.createQiwiPayForm();
        //String paymentUrl = qiwiPayFormService.createQiwiPayForm();
        //return "redirect:" + paymentUrl;
    }

    @PostMapping("/test")
    public String test(@RequestBody String cartName) {
        System.out.println("TEST    " + cartName + " !!!!!!!!!!!!!!");
        return "OK";
    }

}
