package fr.jmaniquet.poc.storedcall.finduser;

import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.NB_USERS;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER1_BIRTHDATE;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER1_GIVENNAME;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER1_ID;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER1_NAME;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER2_GIVENNAME;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER2_ID;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER2_NAME;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER3_BIRTHDATE;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER3_GIVENNAME;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER3_ID;
import static fr.jmaniquet.poc.tools.core.constants.TestDataConstants.USER3_NAME;

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

import fr.jmaniquet.poc.storedcall.StoredCallContext;
import fr.jmaniquet.poc.storedcall.test.context.StoredCallEmbaddedDataBaseContext;
import fr.jmaniquet.poc.tools.core.user.User;
import fr.jmaniquet.poc.tools.core.user.UserBuilder;
import fr.jmaniquet.poc.tools.core.user.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		StoredCallEmbaddedDataBaseContext.class,
		StoredCallContext.class}
)
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/selectuser-dataset.xml")
public class FindUserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private FindUserService underTest;
	
	@Test
	public void testFindAllUsers() {
		FindAllUsersResult result = underTest.findAllUsers();
		
		List<User> orderedAsc = result.getOrderedAsc();
		List<User> orderedDesc = result.getOrderedDesc();
		
		assertAllUsers(orderedAsc, orderedDesc);
	}
	
	@Test
	public void testFindAllUsersAndProperty() {
		FindAllUsersAndPropertyResult result = underTest.findAllUsersAndProperty();
		
		List<User> orderedAsc = result.getOrderedAsc();
		List<User> orderedDesc = result.getOrderedDesc();
		
		assertAllUsers(orderedAsc, orderedDesc);
		Assert.assertEquals("TEST", result.getProperty());
	}
	
	@Test
	public void testFindUserById() {
		User expectedUser = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(USER1_BIRTHDATE).build();
		User actualUser = underTest.findUserById(USER1_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testFindUserByIdWithBirthDateNull() {
		User expectedUser = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		User actualUser = underTest.findUserById(USER2_ID);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	private void assertAllUsers(List<User> orderedAsc, List<User> orderedDesc) {
		User expectedUser1 = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(USER1_BIRTHDATE).build();
		User expectedUser2 = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		User expectedUser3 = UserBuilder.builder().id(USER3_ID).name(USER3_NAME).givenName(USER3_GIVENNAME).birthDate(USER3_BIRTHDATE).build();
		
		Assert.assertEquals(NB_USERS, orderedAsc.size());
		Assert.assertEquals(NB_USERS, orderedDesc.size());
		
		User u1 = orderedAsc.get(0);
		userUtils.assertEquals(expectedUser1, u1);
		
		User u2 = orderedAsc.get(1);
		userUtils.assertEquals(expectedUser2, u2);
		
		User u3 = orderedAsc.get(2);
		userUtils.assertEquals(expectedUser3, u3);
		
		User u4 = orderedDesc.get(0);
		userUtils.assertEquals(expectedUser3, u4);
		
		User u5 = orderedDesc.get(1);
		userUtils.assertEquals(expectedUser2, u5);
		
		User u6 = orderedDesc.get(2);
		userUtils.assertEquals(expectedUser1, u6);
	}
}
