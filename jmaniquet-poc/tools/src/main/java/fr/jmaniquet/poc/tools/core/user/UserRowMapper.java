package fr.jmaniquet.poc.tools.core.user;

import static fr.jmaniquet.poc.tools.core.user.UserColumns.BIRTH_DATE;
import static fr.jmaniquet.poc.tools.core.user.UserColumns.GIVEN_NAME;
import static fr.jmaniquet.poc.tools.core.user.UserColumns.ID;
import static fr.jmaniquet.poc.tools.core.user.UserColumns.NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		long rId = rs.getLong(ID);
		String rName = rs.getString(NAME);
		String rGivenName = rs.getString(GIVEN_NAME);
		Timestamp birthDateTs = rs.getTimestamp(BIRTH_DATE);
		DateTime birthDate = (rs.wasNull() ? null : new DateTime(birthDateTs.getTime()));
		
		return UserBuilder.builder()
				.id(rId)
				.name(rName)
				.givenName(rGivenName)
				.birthDate(birthDate)
				.build();
	}
}