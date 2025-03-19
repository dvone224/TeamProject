package kr.smartReciFit.mybatis.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.smartReciFit.model.recipe.tags.KoreanNamedEnum;

public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

	private final Class<E> TYPE;

	public EnumTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.TYPE = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		// Enum -> 문자열 (DB에 저장)
		ps.setString(i, parameter.name());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// 문자열 -> Enum (DB에서 가져오기)
		String value = rs.getString(columnName);
		return value != null ? Enum.valueOf(TYPE, value) : null;
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		return value != null ? Enum.valueOf(TYPE, value) : null;
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		return value != null ? Enum.valueOf(TYPE, value) : null;
	}
}
