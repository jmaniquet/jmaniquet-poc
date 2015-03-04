package fr.jmaniquet.poc.tools.user;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
class UserUtilsImpl implements UserUtils {
	
	static final String SQL_SELECT_USER_BY_ID = "SELECT U.* FROM USERS U WHERE ID = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RowMapper<User> userRowMapper;
	
	@Override
	public void assertEquals(User expectedUser, User actualUser) {
		Assert.assertNotNull(actualUser);
		Assert.assertEquals(expectedUser.getId(), actualUser.getId());
		Assert.assertEquals(expectedUser.getName(), actualUser.getName());
		Assert.assertEquals(expectedUser.getGivenName(), actualUser.getGivenName());
		Assert.assertEquals(expectedUser.getBirthDate(), actualUser.getBirthDate());
	}
	
	@Override
	public User findUserById(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, userRowMapper, id);
	}
}
