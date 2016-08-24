package com.baomidou.springwind.service.support;

import com.baomidou.framework.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.mapper.AutoMapper;

public class BaseServiceImpl<M extends AutoMapper<T>, T> extends ServiceImpl<M, T, Long> {

}
