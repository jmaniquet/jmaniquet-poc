package fr.jmaniquet.poc.jdbctemplate.jodatime;

import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER1_BIRTHDATE;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER1_GIVENNAME;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER1_ID;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER1_NAME;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER2_GIVENNAME;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER2_ID;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER2_NAME;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER3_BIRTHDATE;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER3_GIVENNAME;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER3_ID;
import static fr.jmaniquet.poc.tools.constants.TestDataConstants.USER3_NAME;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import fr.jmaniquet.poc.tools.user.User;
import fr.jmaniquet.poc.tools.user.UserBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/jdbctemplate-jodatime-test-context.xml"})
@TestExecutionListeners(listeners = DbUnitTestExecutionListener.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@DatabaseSetup("classpath:/selectuser-dataset.xml")
public class JodaJdbcTemplateSelectTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String SQL_SELECT_BIRTHDATE = "SELECT U.BIRTH_DATE FROM USERS U WHERE ID = ?";
	private static final String SQL_SELECT_ENTRY = "SELECT U.* FROM USERS U WHERE ID = ?";
	private static final String SQL_SELECT_ALL_ENTRIES = "SELECT U.* FROM USERS U ORDER BY ID ASC";
	private static final String SQL_SELECT_ALL_DATES = "SELECT U.BIRTH_DATE FROM USERS U ORDER BY ID ASC";
	
	@Autowired
	@Qualifier(BeanConstants.CUSTOM_JDBCTEMPLATE)
	private JdbcTemplate underTest;

	@Test
	public void testQueryForListOfDate() {
		List<DateTime> resultList = underTest.queryForList(SQL_SELECT_ALL_DATES, DateTime.class);
		Assert.assertEquals(3, resultList.size());
		
		DateTime actual1 = resultList.get(0);
		Assert.assertEquals(USER1_BIRTHDATE, actual1);
		
		DateTime actual2 = resultList.get(1);
		Assert.assertNull(actual2);
		
		DateTime actual3 = resultList.get(2);
		Assert.assertEquals(USER3_BIRTHDATE, actual3);
	}
	
	@Test
	public void testQueryForListOfEntries() {
		User expectedUser1 = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(USER1_BIRTHDATE).build();
		User expectedUser2 = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		User expectedUser3 = UserBuilder.builder().id(USER3_ID).name(USER3_NAME).givenName(USER3_GIVENNAME).birthDate(USER3_BIRTHDATE).build();
		
		List<Map<String, Object>> resultList = underTest.queryForList(SQL_SELECT_ALL_ENTRIES);
		Assert.assertEquals(3, resultList.size());
		
		Map<String, Object> userEntry1 = resultList.get(0);
		Map<String, Object> userEntry2 = resultList.get(1);
		Map<String, Object> userEntry3 = resultList.get(2);
		
		assertUserEntry(expectedUser1, userEntry1);
		Assert.assertNotNull(userEntry1.get("BIRTH_DATE"));
		
		assertUserEntry(expectedUser2, userEntry2);
		Assert.assertNull(userEntry2.get("BIRTH_DATE"));
		
		assertUserEntry(expectedUser3, userEntry3);
		Assert.assertNotNull(userEntry3.get("BIRTH_DATE"));
	}
	
	@Test
	public void testQueryForMapWhenDateNotNull() {
		User expectedUser = UserBuilder.builder().id(USER1_ID).name(USER1_NAME).givenName(USER1_GIVENNAME).birthDate(USER1_BIRTHDATE).build();
		Map<String, Object> userEntry = underTest.queryForMap(SQL_SELECT_ENTRY, USER1_ID);
		assertUserEntry(expectedUser, userEntry);
		Assert.assertNotNull(userEntry.get("BIRTH_DATE"));
	}
	
	@Test
	public void testQueryForMapWhenDateNull() {
		User expectedUser = UserBuilder.builder().id(USER2_ID).name(USER2_NAME).givenName(USER2_GIVENNAME).birthDate(null).build();
		Map<String, Object> userEntry = underTest.queryForMap(SQL_SELECT_ENTRY, USER2_ID);
		assertUserEntry(expectedUser, userEntry);
		Assert.assertNull(userEntry.get("BIRTH_DATE"));
	}
	
	@Test
	public void testQueryForObjectWhenDateNotNull() {
		DateTime result = underTest.queryForObject(SQL_SELECT_BIRTHDATE, DateTime.class, USER1_ID);
		Assert.assertNotNull(result);
		Assert.assertEquals(USER1_BIRTHDATE, result);	
	}
	
	@Test
	public void testQueryForObjectWhenDateNull() {
		Object [] args = {USER2_ID};
		Object birthDate = underTest.queryForObject(SQL_SELECT_BIRTHDATE, args, Object.class);
		Assert.assertNull(birthDate);
	}
	
	private void assertUserEntry(User expectedUser, Map<String, Object> userEntry) {
		Assert.assertEquals(expectedUser.getId(), userEntry.get("ID"));
		Assert.assertEquals(expectedUser.getName(), userEntry.get("NAME"));
		Assert.assertEquals(expectedUser.getGivenName(), userEntry.get("GIVEN_NAME"));
		Assert.assertEquals(expectedUser.getBirthDate(), (DateTime) userEntry.get("BIRTH_DATE"));
	}
}
