package com.zh.framework.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;

/**
 * created by lihuibo on 17-8-29 下午8:42
 */
public class StringBlobHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        InputStream inputStream = new ByteArrayInputStream(parameter.getBytes());
        ps.setBlob(i, inputStream);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Blob blob = rs.getBlob(columnName);
        byte[] bytes = blob.getBytes(1, (int) blob.length());
        return new String(bytes, Charset.forName("UTF-8"));
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Blob blob = rs.getBlob(columnIndex);
        byte[] bytes = blob.getBytes(1, (int) blob.length());
        return new String(bytes, Charset.forName("UTF-8"));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Blob blob = cs.getBlob(columnIndex);
        byte[] bytes = blob.getBytes(1, (int) blob.length());
        return new String(bytes, Charset.forName("UTF-8"));
    }
}
