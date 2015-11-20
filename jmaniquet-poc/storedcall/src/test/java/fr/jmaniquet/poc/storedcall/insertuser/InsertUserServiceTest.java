package fr.jmaniquet.poc.storedcall.insertuser;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jmaniquet.poc.storedcall.StoredCallContext;
import fr.jmaniquet.poc.storedcall.test.context.StoredCallEmbaddedDataBaseContext;
import fr.jmaniquet.poc.tools.core.random.RandomUtils;
import fr.jmaniquet.poc.tools.core.user.User;
import fr.jmaniquet.poc.tools.core.user.UserBuilder;
import fr.jmaniquet.poc.tools.core.user.UserUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		StoredCallEmbaddedDataBaseContext.class,
		StoredCallContext.class}
)
public class InsertUserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static final String OVERLOAD_GIVEN_NAME1 = "overloadGivenName1";

	private static final String OVERLOAD_NAME1 = "overloadName1";

	private static final String OVERLOAD_GIVEN_NAME2 = "overloadGivenName2";

	private static final String OVERLOAD_NAME2 = "overloadName2";

	@Autowired
	private UserUtils userUtils;
	
	@Autowired
	private InsertUserService underTest;
	
	@Test
	public void testInsert() {
		Long id = RandomUtils.randomId();
		DateTime birthDateJoda = RandomUtils.randomDate();
		User expectedUser = UserBuilder.builder().id(id).name(OVERLOAD_NAME2).givenName(OVERLOAD_GIVEN_NAME2).birthDate(birthDateJoda).build();
		
		underTest.insertUser(id, birthDateJoda);
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNullDefault() {
		Long id = RandomUtils.randomId();
		User expectedUser = UserBuilder.builder().id(id).name(OVERLOAD_NAME1).givenName(OVERLOAD_GIVEN_NAME1).birthDate(null).build();
		
		underTest.insertUser(id);
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNullParam() {
		Long id = RandomUtils.randomId();
		User expectedUser = UserBuilder.builder().id(id).name(OVERLOAD_NAME2).givenName(OVERLOAD_GIVEN_NAME2).birthDate(null).build();
		
		underTest.insertUser(id, null);
		User actualUser = userUtils.findUserById(id);
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
