package fr.jmaniquet.poc.mybatis.jodatime.mapper;

import org.apache.ibatis.annotations.Param;

import fr.jmaniquet.poc.tools.user.User;

public interface UserMapper extends GenericMapper {	
	
	public User findById(@Param("id") Long id);
}
