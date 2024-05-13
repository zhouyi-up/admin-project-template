package com.liuujun.mshop.common;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyi
 *
 */
@Slf4j
@Component
@MappedTypes({List.class, ArrayList.class})
public class MybatisList2JsonTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        log.info("parameter {}", JsonUtils.toJsonString(parameter));
        ps.setString(i, JsonUtils.toJsonString(parameter));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        if (StringUtils.isBlank(string)) {
            return Lists.newArrayList();
        }
        return JsonUtils.parse(string, List.class);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        if (StringUtils.isBlank(string)) {
            return Lists.newArrayList();
        }
        return JsonUtils.parse(string, List.class);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        if (StringUtils.isBlank(string)) {
            return Lists.newArrayList();
        }
        return JsonUtils.parse(string, List.class);
    }
}
