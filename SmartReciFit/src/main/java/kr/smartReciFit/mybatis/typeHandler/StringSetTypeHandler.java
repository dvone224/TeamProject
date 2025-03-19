package kr.smartReciFit.mybatis.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class StringSetTypeHandler extends BaseTypeHandler<Set<String>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Set<String> parameter, JdbcType jdbcType)
			throws SQLException {

		ps.setString(i, String.join(",", parameter));
	}

	@Override
	public Set<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		return value != null ? new HashSet<>(Arrays.asList(value.split(","))) : null;
	}

	@Override
	public Set<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		return value != null ? new HashSet<>(Arrays.asList(value.split(","))) : null;
	}

	@Override
	public Set<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		return value != null ? new HashSet<>(Arrays.asList(value.split(","))) : null;
	}

}
