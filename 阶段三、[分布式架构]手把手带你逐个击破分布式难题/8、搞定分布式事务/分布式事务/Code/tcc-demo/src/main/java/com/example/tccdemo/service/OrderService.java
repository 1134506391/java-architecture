package com.example.tccdemo.service;

import com.example.tccdemo.db132.dao.OrderMapper;
import com.example.tccdemo.db132.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    /**
     * 本地消息表-订单回调接口
     *
     * @param orderId
     * @return 0:成功 1:订单不存在
     */
    public int handleOrder(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
//        throw new RuntimeException("系统异常");
        if (order == null) {
            return 1;
        }
        //已支付
        order.setOrderStatus(1);
        order.setUpdateTime(new Date());
        //系统更新
        order.setUpdateUser(0);
        orderMapper.updateByPrimaryKey(order);
        return 0;
    }
}


