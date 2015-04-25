package fr.jmaniquet.poc.mybatis.jodatime.handler;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jmaniquet.poc.mybatis.jodatime.mapper.UserMapper;
import fr.jmaniquet.poc.tools.core.random.RandomUtils;
import fr.jmaniquet.poc.tools.core.user.User;
import fr.jmaniquet.poc.tools.core.user.UserBuilder;
import fr.jmaniquet.poc.tools.core.user.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/mybatis-jodatime-test-context.xml"})
public class JodaTimeHandlerInsertTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private UserMapper underTest;
	
	private Long id = RandomUtils.randomId();
	private String name = "fakeName";
	private String givenName = "fakeGivenName";
	
	@Test
	public void testInsertWithBirthDateNotNull() {
		DateTime birthDate = RandomUtils.randomDate();
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(birthDate).build();
		
		underTest.insert(expectedUser);
		
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNull() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).build();
		underTest.insert(expectedUser);
		
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
