package fr.jmaniquet.poc.storedcall.finduser;

import static fr.jmaniquet.poc.storedcall.utils.TestDataConstants.USER1_BIRTHDATE;
import static fr.jmaniquet.poc.storedcall.utils.TestDataConstants.USER1_GIVENNAME;
import static fr.jmaniquet.poc.storedcall.utils.TestDataConstants.USER1_ID;
import static fr.jmaniquet.poc.storedcall.utils.TestDataConstants.USER1_NAME;
import static fr.jmaniquet.poc.storedcall.utils.TestDataConstants.USER2_GIVENNAME;
import static fr.jmaniquet.poc.storedcall.utils.TestDataConstants.USER2_ID;
import static fr.jmaniquet.poc.storedcall.utils.TestDataConstants.USER2_NAME;

import java.util.List;

import org.junit.Assert;
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

import fr.jmaniquet.poc.tools.user.User;
import fr.jmaniquet.poc.tools.user.UserBuilder;
import fr.jmaniquet.poc.tools.user.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/storedcall-test-context.xml"})
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/selectuser-dataset.xml")
public class FindUserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private FindUserService findUserService;
	
	@Test
	public void testFindAllUsers() {
		FindAllUsersResult result = findUserService.findAllUsers();
		
		List<User> orderedAsc = result.getOrderedAsc();
		List<User> orderedDesc = result.getOrderedDesc();
		
		assertAllUsers(orderedAsc, orderedDesc);
	}
	
	@Test
	public void testFindAllUsersAndProperty() {
		FindAllUsersAndPropertyResult result = findUserService.findAllUsersAndProperty();
		
		List<User> orderedAsc = result.getOrderedAsc();
		List<User> orderedDesc = result.getOrderedDesc();
		
		assertAllUsers(orderedAsc, orderedDesc);
		Assert.assertEquals("TEST", result.getProperty());
	}
	
	@Test
	public void testFindUserById() {
		User expectedUser = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(USER1_BIRTHDATE).build();
		User actualUser = findUserService.findUserById(USER1_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testFindUserByIdWithBirthDateNull() {
		User expectedUser = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		User actualUser = findUserService.findUserById(USER2_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	private void assertAllUsers(List<User> orderedAsc, List<User> orderedDesc) {
		User expectedUser1 = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(USER1_BIRTHDATE).build();
		User expectedUser2 = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		
		Assert.assertEquals(2, orderedAsc.size());
		Assert.assertEquals(2, orderedDesc.size());
		
		User u1 = orderedAsc.get(0);
		userUtils.assertEquals(expectedUser1, u1);
		
		User u2 = orderedAsc.get(1);
		userUtils.assertEquals(expectedUser2, u2);		
		
		User u3 = orderedDesc.get(0);
		userUtils.assertEquals(expectedUser2, u3);
		
		User u4 = orderedDesc.get(1);
		userUtils.assertEquals(expectedUser1, u4);
	}
}
