package fr.jmaniquet.poc.storedcall.insertuser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jmaniquet.poc.storedcall.insertuser.InsertUserService;
import fr.jmaniquet.poc.tools.random.RandomUtils;
import fr.jmaniquet.poc.tools.user.User;
import fr.jmaniquet.poc.tools.user.UserBuilder;
import fr.jmaniquet.poc.tools.user.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/storedcall-test-context.xml"})
public class InsertUserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String GIVEN_NAME = "givenName";

	private static final String NAME = "name";

	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private InsertUserService insertUserService;
	
	@Test
	public void testInsertWithBirthDateNullDefault() {
		Long id = RandomUtils.randomId();
		User expectedUser = UserBuilder.builder().id(id).name(NAME).givenName(GIVEN_NAME).birthDate(null).build();
		
		insertUserService.insertUser(id);
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
