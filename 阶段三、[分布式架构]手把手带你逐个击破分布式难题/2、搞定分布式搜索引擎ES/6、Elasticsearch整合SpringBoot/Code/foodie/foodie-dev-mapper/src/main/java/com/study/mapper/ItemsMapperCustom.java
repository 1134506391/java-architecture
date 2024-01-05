package com.study.mapper;

import com.study.pojo.vo.ItemCommentVO;
import com.study.pojo.vo.SearchItemsVO;
import com.study.pojo.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {

    // 根据商品id查询商品的评价
    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);
    // 搜索商品列表
    public List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);
    // 根据分类id搜索商品列表
    public List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);
    // 根据商品规格ids查找最新的商品数据
    public List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);
    // 减少库存[乐观锁]
    public int decreaseItemSpecStock(@Param("specId") String specId,
                                     @Param("pendingCounts") int pendingCounts);
}