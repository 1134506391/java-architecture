package com.study.mapper;

import com.study.pojo.vo.CategoryVO;
import com.study.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    // 根据一级分类id查询子分类信息
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    // 查询每个一级分类下的最新6条商品数据
    public List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);

}