package fr.jmaniquet.poc.storedcall.countusers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import fr.jmaniquet.poc.storedcall.core.StoredCall;
import fr.jmaniquet.poc.storedcall.core.StoredCallResult;
import fr.jmaniquet.poc.storedcall.core.params.integer.TestOut;

@Service
public class CountUserServicempl implements CountUserService {

	@Autowired
	@Qualifier("countUsers")
	private StoredCall countUsers;
	
	private SimpleJdbcCall countUsersWithIn;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void init() {
		countUsersWithIn = new SimpleJdbcCall(jdbcTemplate)
			//.withSchemaName(null)
			//.withFunctionName("countUsersWithIn")
			.withFunctionName("countUsersWithIn")
			.useInParameterNames("FUNCTION_RESULT")
			//.withoutProcedureColumnMetaDataAccess()
			//.withoutProcedureColumnMetaDataAccess()
			.withoutProcedureColumnMetaDataAccess()
			.declareParameters(//new TestOut("FUNCTION_RESULT"),
								//new SqlOutParameter("FUNCTION_RESULT", Types.INTEGER),
								//new IntegerAsIntegerSqlReturnResultSet("FUNCTION_RESULT"),
								//new IntegerAsIntegerSqlInParameter("INCREMENT_IN")
								new SqlParameter("PARAM_IN", Types.INTEGER),
								new TestOut("FUNCTION_RESULT")
								//new SqlOutParameter("FUNCTION_RESULT", Types.INTEGER)
								//new IntegerAsIntegerSqlReturnResultSet("FUNCTION_RESULT")
								)
			//.withReturnValue()
			;
	}
	
	@Override
	public Integer countUsers() {
		StoredCallResult result = countUsers.execute();
		return result.getIntegerFunctionResult();
	}
	
	@Override
	public Integer countUsersWithIn(int incrementIn) {
		//SqlParameterSource sps = new MapSqlParameterSource().addValue("INCREMENT_IN", incrementIn, Types.INTEGER);
		//return countUsersWithIn.executeFunction(Integer.class, sps);
		//return countUsersWithIn.executeFunction(Integer.class, incrementIn);
		//return countUsersWithIn.executeObject(Integer.class, incrementIn);
		//Map<String, Object> resultMap = countUsersWithIn.execute(incrementIn);
		/*Map<String, Object> resultMap = countUsersWithIn.execute(incrementIn);
		Integer nbUsers = (Integer) resultMap.get("FUNCTION_RETURN");*/
		//Integer incrementOut = (Integer) resultMap.get("INCREMENT_OUT");
		
		jdbcTemplate.execute("{? = call countUsersWithIn(?)}", new Toto(incrementIn));
		
		//Integer executeObject = countUsersWithIn.executeObject(Integer.class, incrementIn);
		//return nbUsers;
		return null;
	}
	
	private static class Toto implements CallableStatementCallback<CountUsersWithInAndOutResult> {

		private int in;
		
		public Toto(int in) {
			super();
			this.in = in;
		}

		@Override
		public CountUsersWithInAndOutResult doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, in);
			cs.execute();
			// TODO Auto-generated method stub
			return null;
		}

		
		
	}
}
