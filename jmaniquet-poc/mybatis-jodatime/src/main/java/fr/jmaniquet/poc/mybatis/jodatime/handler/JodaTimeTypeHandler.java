package fr.jmaniquet.poc.mybatis.jodatime.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.DateTime;

@MappedTypes(value = DateTime.class)
public class JodaTimeTypeHandler extends BaseTypeHandler<DateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
		Timestamp timeStamp = new Timestamp(parameter.getMillis());
		ps.setTimestamp(i, timeStamp);
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp time = rs.getTimestamp(columnName);
		return convertToDateTime(time);
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp time = rs.getTimestamp(columnIndex);
		return convertToDateTime(time);
	}

	@Override
	public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp time = cs.getTimestamp(columnIndex);
		return convertToDateTime(time);
	}

	private DateTime convertToDateTime(Timestamp pTime) {
		if (pTime == null) {
			return null;
		}
		return new DateTime(pTime.getTime());
	}
}
