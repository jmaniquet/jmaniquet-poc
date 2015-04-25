package fr.jmaniquet.poc.storedcall.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;

import fr.jmaniquet.poc.storedcall.core.params.cursor.CursorParameter;

public class StoredCallBuilder {

	public static NameStep builder(JdbcTemplate jdbcTemplate) {
		return new Steps(jdbcTemplate);
	}

	public static interface NameStep {
		ParametersStep withName(String name);
	}

	public static interface ParametersStep {
		ParametersStep addSqlParameters(SqlParameter ... parameters);

		ParametersStep addCursorParameters(CursorParameter<?> ... cursorParameters);

		StoredCall build();
	}
	
	private static class Steps implements NameStep, ParametersStep {

		private StoredCallImpl call;
		private List<SqlParameter> sqlParameters;
		private List<CursorParameter<?>> cursorParameters;
		
		private Steps(JdbcTemplate jdbcTemplate) {
			this.call = new StoredCallImpl();
			this.sqlParameters = new ArrayList<>();
			this.cursorParameters = new ArrayList<>();
			this.call.setJdbcTemplate(jdbcTemplate);
		}
		
		@Override
		public ParametersStep addSqlParameters(SqlParameter ... parameters) {
			for (SqlParameter sqlParameter : parameters) {
				this.sqlParameters.add(sqlParameter);
			}
			return this;
		}

		@Override
		public ParametersStep addCursorParameters(CursorParameter<?> ... cursorParameters) {
			for (CursorParameter<?> cursorParameter : cursorParameters) {
				this.cursorParameters.add(cursorParameter);
			}
			return this;
		}

		@Override
		public StoredCall build() {
			SqlParameter [] sqlParametersTab = new SqlParameter[this.sqlParameters.size()];
			CursorParameter<?> [] cursorParametersTab = new CursorParameter<?>[this.cursorParameters.size()];
			this.call.setParameters(this.sqlParameters.toArray(sqlParametersTab));
			this.call.setCursorParameters(this.cursorParameters.toArray(cursorParametersTab));
			return this.call;
		}

		@Override
		public ParametersStep withName(String name) {
			this.call.setProcedureName(name);
			return this;
		}
	}
}
