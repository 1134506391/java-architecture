package com.study.controller;

import com.study.pojo.Orders;
import com.study.service.center.MyOrdersService;
import com.study.utils.StudyJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class BaseController {
    // 分页的每一页显示的条数
    public static final Integer COMMON_PAGE_SIZE = 10;
    // 分页的每一页显示的条数
    public static final Integer PAGE_SIZE = 20;
    // 订单前后端联调Cookie值
    public static final String FOODIE_SHOPCART = "shopcart";
    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";
    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                       |-> 回调通知的url
    String payReturnUrl = "http://124.220.161.72:8088/foodie-dev-api/orders/notifyMerchantOrderPaid";

    // 用户上传头像的位置
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
            File.separator + "images" +
            File.separator + "foodie" +
            File.separator + "faces";
//    public static final String IMAGE_USER_FACE_LOCATION = "/workspaces/images/foodie/faces";

    @Autowired
    public MyOrdersService myOrdersService;

    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     * @return
     */
    public StudyJSONResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return StudyJSONResult.errorMsg("订单不存在！");
        }
        return StudyJSONResult.ok(order);
    }
}
