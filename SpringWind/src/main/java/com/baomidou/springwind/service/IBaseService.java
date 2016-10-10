package com.baomidou.springwind.service;

import com.baomidou.framework.service.IService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.BaseEntity;

/**
 * <p>
 * 基本 Service
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-20
 */
public interface IBaseService<T extends BaseEntity> extends IService<T, Long> {

	public Page<T> selectPage(Page<T> page, T entity);

}
