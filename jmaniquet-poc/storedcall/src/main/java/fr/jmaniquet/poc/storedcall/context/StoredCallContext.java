package fr.jmaniquet.poc.storedcall.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fr.jmaniquet.poc.storedcall.core.StoredCall;
import fr.jmaniquet.poc.storedcall.core.StoredCallBuilder;
import fr.jmaniquet.poc.storedcall.core.params.bigint.BigIntAsLongSqlInOutParameter;
import fr.jmaniquet.poc.storedcall.core.params.bigint.BigIntAsLongSqlInParameter;
import fr.jmaniquet.poc.storedcall.core.params.cursor.CursorParameter;
import fr.jmaniquet.poc.storedcall.core.params.timestamp.TimestampAsDateTimeSqlInParameter;
import fr.jmaniquet.poc.storedcall.core.params.timestamp.TimestampAsDateTimeSqlOutParameter;
import fr.jmaniquet.poc.storedcall.core.params.varchar.VarcharAsStringSqlOutParameter;
import fr.jmaniquet.poc.tools.core.ToolsCoreContext;
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
		return StoredCallBuilder
				.builder(jdbcTemplate)
				.withName("findAllUsers")
				.addCursorParameters(
						new CursorParameter<User>("USERS_ASC", userRowMapper),
						new CursorParameter<User>("USERS_DESC", userRowMapper)
						)
				.build();
	}
	
	@Bean
	public StoredCall findAllUsersAndProperty() {
		return StoredCallBuilder
				.builder(jdbcTemplate)
				.withName("findAllUsersAndProperty")
				.addSqlParameters(new VarcharAsStringSqlOutParameter("PROPERTY"))
				.addCursorParameters(
						new CursorParameter<User>("USERS_ASC", userRowMapper),
						new CursorParameter<User>("USERS_DESC", userRowMapper)
						)
				.build();
	}
	
	@Bean
	public StoredCall findUserById() {
		return StoredCallBuilder
				.builder(jdbcTemplate)
				.withName("findUserById")
				.addSqlParameters(
						new BigIntAsLongSqlInOutParameter("ID_P"),
						new VarcharAsStringSqlOutParameter("NAME"),
						new VarcharAsStringSqlOutParameter("GIVEN_NAME"),
						new TimestampAsDateTimeSqlOutParameter("BIRTH_DATE")
						)
				.build();
	}
	
	@Bean
	public StoredCall insertUserOverload1() {
		return StoredCallBuilder
				.builder(jdbcTemplate)
				.withName("insertUser")
				.addSqlParameters(new BigIntAsLongSqlInParameter("ID"))
				.build();
	}
	
	@Bean
	public StoredCall insertUserOverload2() {
		return StoredCallBuilder
				.builder(jdbcTemplate)
				.withName("insertUser")
				.addSqlParameters(
						new BigIntAsLongSqlInParameter("ID"),
						new TimestampAsDateTimeSqlInParameter("BIRTH_DATE")
						)
				.build();
	}
}
