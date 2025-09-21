package com.hmall.user.service.impl;

import com.hmall.api.domain.po.Address;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.user.mapper.AddressMapper;
import com.hmall.user.service.IAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2023-05-05
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

}
