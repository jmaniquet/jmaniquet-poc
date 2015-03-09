package fr.jmaniquet.poc.mybatis.jodatime.handler;

import static fr.jmaniquet.poc.testutils.TestDataConstants.BIRTHDATE_TEST;
import static fr.jmaniquet.poc.testutils.TestDataConstants.USER1_GIVENNAME;
import static fr.jmaniquet.poc.testutils.TestDataConstants.USER1_ID;
import static fr.jmaniquet.poc.testutils.TestDataConstants.USER1_NAME;
import static fr.jmaniquet.poc.testutils.TestDataConstants.USER2_GIVENNAME;
import static fr.jmaniquet.poc.testutils.TestDataConstants.USER2_ID;
import static fr.jmaniquet.poc.testutils.TestDataConstants.USER2_NAME;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import fr.jmaniquet.poc.mybatis.jodatime.mapper.UserMapper;
import fr.jmaniquet.poc.tools.user.User;
import fr.jmaniquet.poc.tools.user.UserBuilder;
import fr.jmaniquet.poc.tools.user.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/mybatis-jodatime-test-context.xml"})
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/selectuser-dataset.xml")
public class JodaTimeHandlerFindByIdTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private UserMapper underTest;
	
	@Before
	public void toto() {
		Object o = getClass().getResource("mappers/userMapper.xml");
		System.out.println(o);
	}
	
	@Test
	public void testFindByIdWithBirthDateNotNull() {
		User expectedUser = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(BIRTHDATE_TEST).build();
		User actualUser = underTest.findById(USER1_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testFindByIdWithBirthDateNull() {
		User expectedUser = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		User actualUser = underTest.findById(USER2_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
