package com.geekbrains.spring.web.order.services;

import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.PaymentInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
public class QiwiPayFormService {

    public String createQiwiPayForm() {
        System.out.println("createQiwiPayForm() - Service");

        String secretKey = "eyJ2ZXJzaW9uIjoiUDJQIiwiZGF0YSI6eyJwYXlpbl9tZXJjaGFudF9zaXRlX3VpZCI6ImlyNDA2Yi0wMCIsInVzZXJfaW" +
                "QiOiI3OTExOTM0OTIzNyIsInNlY3JldCI6IjNkZTE2YTI0NmY2MWRlMzViMTJkYWMwNDFmM2NkODJkZjJhZmFmYmUzMWEyYmZlOTJhYWJi" +
                "MGFkNWVkZmNiYTQifX0=";
        BillPaymentClient client = BillPaymentClientFactory.createDefault(secretKey);
        String publicKey = "48e7qUxn9T7RyYE1MVZswX1FRSbE6iyCj2gCRwwF3Dnh5XrasNTx3BGPiMsyXQFNKQhvukniQG8RTVhYm3iPsyFk4R1FXv" +
                "fSx6QV6zzkWSC35X8nNfMVHu41woN87RkaFWV34LADtjM3qs9WUPV3Q55p8DUpNyBxTUVENZRTsBDjwv358VuqqoK68DATg";
        MoneyAmount moneyAmount = new MoneyAmount(
                BigDecimal.valueOf(20),
                Currency.getInstance("RUB")
        );
        String billId = "1";
        String successUrl = "";

        String paymentUrl = client.createPaymentForm(
                new PaymentInfo(publicKey, moneyAmount, billId, successUrl)
        );

        System.out.println(paymentUrl);
        return paymentUrl;
    }
}
