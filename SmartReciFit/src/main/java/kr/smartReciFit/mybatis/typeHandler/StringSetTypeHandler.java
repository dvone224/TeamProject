package kr.smartReciFit.mybatis.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.json.simple.JSONArray;

public class StringSetTypeHandler extends BaseTypeHandler<Set<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<String> parameter, JdbcType jdbcType) throws SQLException {
        // Set을 JSON 문자열로 변환하여 DB에 저장
        JSONArray jsonArray = new JSONArray();
        for (String value : parameter) {
            jsonArray.add(value);
        }
        ps.setString(i, jsonArray.toJSONString());
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return json != null ? parseJson(json) : null;
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return json != null ? parseJson(json) : null;
    }

    @Override
    public Set<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return json != null ? parseJson(json) : null;
    }

    private Set<String> parseJson(String json) {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = new JSONArray();
        } catch (Exception e) {
            // JSON 파싱 실패 시 빈 집합 반환
            return new HashSet<>();
        }
        Set<String> result = new HashSet<>();
        for (Object obj : jsonArray) {
            result.add((String) obj);
        }
        return result;
    }

}
