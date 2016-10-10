package com.baomidou.springwind.service.support;

import com.baomidou.framework.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

public class BaseServiceImpl<M extends AutoMapper<T>, T> extends ServiceImpl<M, T, Long> {

	public Page<T> selectPage(Page<T> page, T entity) {
		return selectPage(page, new EntityWrapper<T>(entity));
	}

}
