package com.example.tccdemo.controller;

import com.example.tccdemo.service.PaymentServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PaymentController {
    @Autowired
    private PaymentServcie paymentServcie;
    // 本地消息表（支付接口）
    @RequestMapping("payment")
    public String payment(int userId, int orderId, BigDecimal amount) throws Exception {
        // 调用支付接口[本地消息表]
//        int result = paymentServcie.pament(userId, orderId, amount);
        // 调用支付接口[消息队列]
        int result = paymentServcie.pament(userId, orderId, amount);
        return "支付结果：" + result;
    }
}
