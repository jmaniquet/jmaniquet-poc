package fr.jmaniquet.poc.storedcall.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fr.jmaniquet.poc.storedcall.core.StoredCall;
import fr.jmaniquet.poc.storedcall.core.StoredCallImpl;
import fr.jmaniquet.poc.storedcall.core.params.bigint.BigIntAsLongSqlInOutParameter;
import fr.jmaniquet.poc.storedcall.core.params.bigint.BigIntAsLongSqlInParameter;
import fr.jmaniquet.poc.storedcall.core.params.cursor.CursorParameter;
import fr.jmaniquet.poc.storedcall.core.params.timestamp.TimestampAsDateTimeSqlInParameter;
import fr.jmaniquet.poc.storedcall.core.params.timestamp.TimestampAsDateTimeSqlOutParameter;
import fr.jmaniquet.poc.storedcall.core.params.varchar.VarcharAsStringSqlOutParameter;
import fr.jmaniquet.poc.tools.core.context.ToolsCoreContext;
import fr.jmaniquet.poc.tools.core.user.User;

@Configuration
@Import(ToolsCoreContext.class)
@ComponentScan("fr.jmaniquet.poc.storedcall")
public class StoredCallContext {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RowMapper<User> userRowMapper;
	
	@Bean
	public StoredCall findAllUsers() {
		StoredCallImpl call = new StoredCallImpl();
		call.setJdbcTemplate(jdbcTemplate);
		call.setProcedureName("findAllUsers");
		call.setCursorParameters(
				new CursorParameter<User>("USERS_ASC", userRowMapper),
				new CursorParameter<User>("USERS_DESC", userRowMapper)
			);
		return call;
	}
	
	@Bean
	public StoredCall findAllUsersAndProperty() {
		StoredCallImpl call = new StoredCallImpl();
		call.setJdbcTemplate(jdbcTemplate);
		call.setProcedureName("findAllUsersAndProperty");
		call.setParameters(new VarcharAsStringSqlOutParameter("PROPERTY"));
		call.setCursorParameters(
				new CursorParameter<User>("USERS_ASC", userRowMapper),
				new CursorParameter<User>("USERS_DESC", userRowMapper)
			);
		return call;
	}
	
	@Bean
	public StoredCall findUserById() {
		StoredCallImpl call = new StoredCallImpl();
		call.setJdbcTemplate(jdbcTemplate);
		call.setProcedureName("findUserById");
		call.setParameters(
				new BigIntAsLongSqlInOutParameter("ID_P"),
				new VarcharAsStringSqlOutParameter("NAME"),
				new VarcharAsStringSqlOutParameter("GIVEN_NAME"),
				new TimestampAsDateTimeSqlOutParameter("BIRTH_DATE")
			);
		return call;
	}
	
	@Bean
	public StoredCall insertUserOverload1() {
		StoredCallImpl call = new StoredCallImpl();
		call.setJdbcTemplate(jdbcTemplate);
		call.setProcedureName("insertUser");
		call.setParameters(new BigIntAsLongSqlInParameter("ID"));
		return call;
	}
	
	@Bean
	public StoredCall insertUserOverload2() {
		StoredCallImpl call = new StoredCallImpl();
		call.setJdbcTemplate(jdbcTemplate);
		call.setProcedureName("insertUser");
		call.setParameters(
				new BigIntAsLongSqlInParameter("ID"),
				new TimestampAsDateTimeSqlInParameter("BIRTH_DATE")
			);
		return call;
	}
}
