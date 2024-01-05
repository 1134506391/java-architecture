package com.study.controller;

import com.study.service.ItemsESService;
import com.study.utils.PagedGridResult;
import com.study.utils.StudyJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("items")
public class ItemsController {

    @Autowired
    private ItemsESService itemsESService;

    @GetMapping("/hello")
    public Object hello() {
        return "Hello Elasticsearch~";
    }

    @GetMapping("/es/search")
    public StudyJSONResult search(String keywords, String sort, Integer page, Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            return StudyJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 20;
        }

        page--;
        PagedGridResult grid = itemsESService.searhItems(keywords,
                sort,
                page,
                pageSize);
        return StudyJSONResult.ok(grid);
    }

}
