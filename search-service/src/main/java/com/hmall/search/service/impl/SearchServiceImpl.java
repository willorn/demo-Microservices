package com.hmall.search.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.domain.dto.ItemDTO;
import com.hmall.common.domain.PageDTO;
import com.hmall.search.domain.po.Item;
import com.hmall.search.domain.query.ItemPageQuery;
import com.hmall.search.mapper.ItemMapper;
import com.hmall.search.service.ISearchService;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl extends ServiceImpl<ItemMapper, Item> implements ISearchService {

    @Override
    public PageDTO<ItemDTO> search(ItemPageQuery query) {
        Page<Item> result = lambdaQuery()
                .like(StrUtil.isNotBlank(query.getKey()), Item::getName, query.getKey())
                .eq(StrUtil.isNotBlank(query.getBrand()), Item::getBrand, query.getBrand())
                .eq(StrUtil.isNotBlank(query.getCategory()), Item::getCategory, query.getCategory())
                .eq(Item::getStatus, 1)
                .between(query.getMaxPrice() != null, Item::getPrice, query.getMinPrice(), query.getMaxPrice())
                .page(query.toMpPage("update_time", false));
        return PageDTO.of(result, ItemDTO.class);
    }
}
