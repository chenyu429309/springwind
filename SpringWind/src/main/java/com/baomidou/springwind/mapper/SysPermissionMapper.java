package com.baomidou.springwind.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.springwind.entity.SysPermission;
import com.baomidou.springwind.entity.vo.MenuTreeVO;
import com.baomidou.springwind.entity.vo.MenuVO;

/**
 *
 * Permission 表数据库控制层接口
 *
 */
public interface SysPermissionMapper extends AutoMapper<SysPermission> {

	List<MenuVO> selectMenuByUserId(@Param("userId") Long userId, @Param("pid") Long pid);

	List<SysPermission> selectAllByUserId(@Param("userId") Long userId);

	List<MenuTreeVO> selectByPid(@Param("pid") Long pid);

}