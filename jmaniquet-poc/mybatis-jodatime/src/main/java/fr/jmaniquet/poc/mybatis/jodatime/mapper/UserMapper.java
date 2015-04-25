package fr.jmaniquet.poc.mybatis.jodatime.mapper;

import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

import fr.jmaniquet.poc.tools.core.user.User;

public interface UserMapper extends GenericMapper {	
	
	public User findById(@Param("id") Long id);
	
	public void insert(@Param("user") User user);
	
	public void updateBirthDate(@Param("id") Long id, @Param("birthDate") DateTime birthDate);
}
