package com.study.service;

import com.study.utils.PagedGridResult;

public interface ItemsESService {

    public PagedGridResult searhItems(String keywords,
                                      String sort,
                                      Integer page,
                                      Integer pageSize);

}
