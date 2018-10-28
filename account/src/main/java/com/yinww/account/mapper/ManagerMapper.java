package com.yinww.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yinww.account.domain.Manager;
import com.yinww.account.domain.Module;

@Mapper
public interface ManagerMapper {

	Manager getManager(@Param("loginName") String username);
	
	List<Module> getModules();

	void updateLanguage(Manager manager);

}
