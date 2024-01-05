package com.study.cart.service;

import com.study.pojo.ShopcartBO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 半仙.
 */
@RequestMapping("cart-api")
public interface CartService {
    // 添加商品购物车
    @PostMapping("addItem")
    public boolean addItemToCart(@RequestParam("userId") String userId,
                                 @RequestBody ShopcartBO shopcartBO);

    // 删除商品购物车
    @PostMapping("removeItem")
    public boolean removeItemFromCart(@RequestParam("userId") String userId,
                                      @RequestParam("itemSpecId") String itemSpecId);

    // 一键清空商品购物车
    @PostMapping("clearCart")
    public boolean clearCart(@RequestParam("userId") String userId);

}
