package com.yinww.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.web.core.domain.Module;

@Mapper
public interface ModuleMapper {
	
	List<Module> getModules(@Param("status") Integer status);

}
