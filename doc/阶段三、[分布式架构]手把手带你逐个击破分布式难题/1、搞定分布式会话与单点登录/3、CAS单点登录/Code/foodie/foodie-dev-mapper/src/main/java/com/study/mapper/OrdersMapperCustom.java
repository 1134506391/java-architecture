package com.study.mapper;

import com.study.pojo.OrderStatus;
import com.study.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapperCustom {

    // 查询我的订单列表
    public List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);
    // 查询用户订单数
    public int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);
    // 获得分页的订单动向
    public List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);
}
