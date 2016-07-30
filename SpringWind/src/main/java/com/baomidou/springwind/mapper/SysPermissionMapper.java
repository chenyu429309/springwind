package com.baomidou.springwind.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.springwind.entity.Menu;
import com.baomidou.springwind.entity.SysPermission;
import com.baomidou.springwind.entity.vo.MenuZtreeVO;

/**
 *
 * Permission 表数据库控制层接口
 *
 */
public interface SysPermissionMapper extends AutoMapper<SysPermission> {

	List<SysPermission> selectAllByUserId(@Param("userId") Long userId);

	List<Menu> selectMenuByPid(@Param("pid") Long pid);

	List<MenuZtreeVO> selectMenuTreeByPid(@Param("pid") Long pid);

}