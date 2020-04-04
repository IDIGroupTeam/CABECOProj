package com.idi.hr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.idi.hr.bean.WorkGroup;

public class WorkGroupMapper implements RowMapper<WorkGroup> {

	public WorkGroup mapRow(ResultSet rs, int nowNum) throws SQLException {
		String groupId = rs.getString("CODE");
		String groupName = rs.getString("NAME");
		String description = rs.getString("DESCRIPTION");

		return new WorkGroup(groupId, groupName, description);
	}
}
