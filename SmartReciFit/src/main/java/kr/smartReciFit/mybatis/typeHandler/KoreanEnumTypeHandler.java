package kr.smartReciFit.mybatis.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import kr.smartReciFit.model.recipe.tags.KoreanNamedEnum;

public class KoreanEnumTypeHandler<E extends Enum<E> & KoreanNamedEnum> extends BaseTypeHandler<E> {

    private final Class<E> TYPE;

    public KoreanEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        if (!KoreanNamedEnum.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException("Type must implement KoreanNamedEnum");
        }
        this.TYPE = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        // Enum -> 문자열 (DB에 저장)
        ps.setString(i, parameter.getKoreanName());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value != null ? KoreanNamedEnum.getEnumByKoreanName((Class<E>) TYPE, value) : null;
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value != null ? KoreanNamedEnum.getEnumByKoreanName((Class<E>) TYPE, value) : null;
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value != null ? KoreanNamedEnum.getEnumByKoreanName((Class<E>) TYPE, value) : null;
    }
}
