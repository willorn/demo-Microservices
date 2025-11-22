package com.hmall.search.service;

import com.hmall.api.domain.dto.ItemDTO;
import com.hmall.common.domain.PageDTO;
import com.hmall.search.domain.query.ItemPageQuery;

public interface ISearchService {

    PageDTO<ItemDTO> search(ItemPageQuery query);
}
