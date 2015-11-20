package fr.jmaniquet.poc.jdbctemplate.jodatime;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jmaniquet.poc.tools.core.random.RandomUtils;
import fr.jmaniquet.poc.tools.core.user.User;
import fr.jmaniquet.poc.tools.core.user.UserBuilder;
import fr.jmaniquet.poc.tools.core.user.UserUtils;
import fr.jmaniquet.poc.tools.test.context.ToolsEmbaddedDataBaseContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ToolsEmbaddedDataBaseContext.class,
		JdbcTemplateJodaTimeContext.class}
)
public class JodaJdbcTemplateInsertTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String SQL_INSERT_ALL_FIELDS = "INSERT INTO USERS (ID, NAME, GIVEN_NAME, BIRTH_DATE) VALUES (?, ?, ?, ?)";
	private static final String SQL_INSERT_WITH_DEFAULT_BIRTHDATE = "INSERT INTO USERS (ID, NAME, GIVEN_NAME) VALUES (?, ?, ?)";
	
	@Autowired
	@Qualifier(BeanConstants.CUSTOM_JDBCTEMPLATE)
	private JdbcTemplate underTest;
	
	@Autowired
	private UserUtils userUtils;
	
	private long id = RandomUtils.randomId();
	private String name = "fakeName";
	private String givenName = "fakeGivenName";
	private DateTime birthDate = RandomUtils.randomDate();
	
	@Test
	public void testInsert() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(birthDate).build();
		underTest.update(SQL_INSERT_ALL_FIELDS, id, name, givenName, birthDate);
		
		User actualUser = userUtils.findUserById(id);
		Assert.assertNotNull(actualUser.getBirthDate());
		Assert.assertEquals(birthDate, actualUser.getBirthDate());
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNullDefault() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(null).build();
		underTest.update(SQL_INSERT_WITH_DEFAULT_BIRTHDATE, id, name, givenName);
		
		User actualUser = userUtils.findUserById(id);
		Assert.assertNull(actualUser.getBirthDate());
		userUtils.assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testInsertWithBirthDateNullParam() {
		User expectedUser = UserBuilder.builder().id(id).name(name).givenName(givenName).birthDate(null).build();
		underTest.update(SQL_INSERT_ALL_FIELDS, id, name, givenName, null);
		
		User actualUser = userUtils.findUserById(id);
		Assert.assertNull(actualUser.getBirthDate());
		userUtils.assertEquals(expectedUser, actualUser);
	}
}
