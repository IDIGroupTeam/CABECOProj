package com.idi.hr.dao;

import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.idi.hr.bean.EmployeeInfo;
import com.idi.hr.bean.WorkGroup;
import com.idi.hr.common.PropertiesManager;
import com.idi.hr.common.Utils;
import com.idi.hr.mapper.EmployeeMapper;
import com.idi.hr.mapper.WorkGroupMapper;

public class EmployeeDAO extends JdbcDaoSupport {

	private static final Logger log = Logger.getLogger(EmployeeDAO.class.getName());
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
	private JdbcTemplate jdbcTmpl;

	public JdbcTemplate getJdbcTmpl() {
		return jdbcTmpl;
	}

	public void setJdbcTmpl(JdbcTemplate jdbcTmpl) {
		this.jdbcTmpl = jdbcTmpl;
	}

	@Autowired
	public EmployeeDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	PropertiesManager hr = new PropertiesManager("cabecohr.properties");

	/**
	 * get employee by employeeId
	 * 
	 * @param employeeId
	 * @return employee object
	 */
	public EmployeeInfo getEmployee(String employeeId) throws Exception{

		String sql = hr.get("GET_EMPLOYEE_INFO").toString();
		log.info("GET_EMPLOYEE_INFO query: " + sql);
		Object[] params = new Object[] { employeeId };

		EmployeeMapper mapper = new EmployeeMapper();
		EmployeeInfo employee = jdbcTmpl.queryForObject(sql, params, mapper);
		
		if(employee.getDOB() != null && employee.getDOB().length() > 0 && employee.getDOB().contains("-"))
			employee.setDOB(Utils.convertDateToDisplay(employee.getDOB()));
		if(employee.getJoinDate() != null && employee.getJoinDate().length() > 0 && employee.getJoinDate().contains("-"))
			employee.setJoinDate(Utils.convertDateToDisplay(employee.getJoinDate()));
		if(employee.getOfficalJoinDate() != null && employee.getOfficalJoinDate().length() > 0 && employee.getOfficalJoinDate().contains("-"))
			employee.setOfficalJoinDate(Utils.convertDateToDisplay(employee.getOfficalJoinDate()));
		if(employee.getIssueDate() != null && employee.getIssueDate().length() > 0 && employee.getIssueDate().contains("-"))
			employee.setIssueDate(Utils.convertDateToDisplay(employee.getIssueDate()));
		if(employee.getExpiryDate() != null && employee.getExpiryDate().length() > 0 && employee.getExpiryDate().contains("-"))
			employee.setExpiryDate(Utils.convertDateToDisplay(employee.getExpiryDate()));
		
		return employee;
	}
	
	/**
	 * Checking account existing or not
	 * 
	 * @param account
	 * @return int
	 *
	 */
	public int getAccount(String account) {

		String sql = hr.get("CHECK_ACCOUNT_DUPLICATE").toString();
		log.info("CHECK_ACCOUNT_DUPLICATE query: " + sql);
		Object[] params = new Object[] { account };
		
		String accountNumber = jdbcTmpl.queryForObject(sql, String.class, params);

		return Integer.parseInt(accountNumber);
	}
	
	/**
	 * countMemberByWorkStatus
	 * @param workstatus
	 * @return
	 */
	public int countMemberByWorkStatus(String workstatus, String department) {

		String sql = hr.get("COUNT_MEMBER_BY_WORK_STATUS").toString();
		if(!department.equalsIgnoreCase("all")) {
			sql = sql + "AND DEPARTMENT IN ('" + department +"')";
		}
		log.info("COUNT_MEMBER_BY_WORK_STATUS query: " + sql);
		Object[] params = new Object[] { workstatus };
		
		String numberEmployee = jdbcTmpl.queryForObject(sql, String.class, params);
		
		return Integer.parseInt(numberEmployee);
	}

	/**
	 * Insert or update a employee into database
	 * 
	 * @param employeeInfo
	 * @throws Exception
	 */
	public void insertOrUpdateEmployee(EmployeeInfo employeeInfo) throws Exception {
		try {
			
			if(employeeInfo.getDOB() != null && employeeInfo.getDOB().length() > 0)
				employeeInfo.setDOB(Utils.convertDateToStore(employeeInfo.getDOB()));
			if(employeeInfo.getJoinDate() != null && employeeInfo.getJoinDate().length() > 0)
				employeeInfo.setJoinDate(Utils.convertDateToStore(employeeInfo.getJoinDate()));
			if(employeeInfo.getOfficalJoinDate() != null && employeeInfo.getOfficalJoinDate().length() > 0)
				employeeInfo.setOfficalJoinDate(Utils.convertDateToStore(employeeInfo.getOfficalJoinDate()));
			if(employeeInfo.getIssueDate() != null && employeeInfo.getIssueDate().length() > 0)
				employeeInfo.setIssueDate(Utils.convertDateToStore(employeeInfo.getIssueDate()));
			if(employeeInfo.getExpiryDate() != null && employeeInfo.getExpiryDate().length() > 0)
				employeeInfo.setExpiryDate(Utils.convertDateToStore(employeeInfo.getExpiryDate()));
			
			if (employeeInfo.getEmployeeId() > 0) {
				// update
				String sql = hr.getProperty("UPDATE_EMPLOYEE_INFO").toString();
				log.info("UPDATE_EMPLOYEE_INFO query: " + sql);
				Object[] params = new Object[] { employeeInfo.getFullName(),
						employeeInfo.getGender(), employeeInfo.getJobTitle(), employeeInfo.getWorkStatus(),
						employeeInfo.getDOB(), employeeInfo.getMaritalStatus(), employeeInfo.getLoginAccount(),
						employeeInfo.getPersonalId(), employeeInfo.getIssueDate(), employeeInfo.getIssuePlace(), employeeInfo.getDepartment(),
						employeeInfo.getPhoneNo(), employeeInfo.getJoinDate(), employeeInfo.getOfficalJoinDate(),
						employeeInfo.getEmail(), employeeInfo.getTerminationDate(), employeeInfo.getReasonforLeave(),
						employeeInfo.getCurrentAdress(), employeeInfo.getPermanentAdress(), employeeInfo.getNote(),
						employeeInfo.getNation(),// employeeInfo.getImage(), 
						employeeInfo.getEmerName(),	employeeInfo.getEmerPhoneNo(), employeeInfo.geteUnion(), employeeInfo.getTaxCode(),
						employeeInfo.getExpiryDate(), employeeInfo.getAcademyLevel(), employeeInfo.getImagePath(), employeeInfo.getWorkGroup(),
						employeeInfo.getSocicalInsuNo(), employeeInfo.getHealthInsuNo(),
						employeeInfo.getPercentSocicalInsu(), employeeInfo.getEmployeeId()};
				jdbcTmpl.update(sql, params);
			} else {
				// insert
				String sql = hr.getProperty("INSERT_EMPLOYEE_INFO").toString();
				log.info("INSERT_EMPLOYEE_INFO query: " + sql);
				Object[] params = new Object[] { employeeInfo.getFullName(),
						employeeInfo.getGender(), employeeInfo.getJobTitle(), employeeInfo.getWorkStatus(),
						employeeInfo.getDOB(), employeeInfo.getMaritalStatus(), employeeInfo.getLoginAccount(),
						employeeInfo.getPersonalId(), employeeInfo.getIssueDate(), employeeInfo.getIssuePlace(), employeeInfo.getDepartment(),
						employeeInfo.getPhoneNo(), employeeInfo.getJoinDate(), employeeInfo.getOfficalJoinDate(),
						employeeInfo.getEmail(), employeeInfo.getTerminationDate(), employeeInfo.getReasonforLeave(),
						employeeInfo.getCurrentAdress(), employeeInfo.getPermanentAdress(), employeeInfo.getNote(),
						employeeInfo.getNation(),// employeeInfo.getImage(), 
						employeeInfo.getEmerName(),
						employeeInfo.getEmerPhoneNo(), employeeInfo.geteUnion(), employeeInfo.getTaxCode(),
						employeeInfo.getExpiryDate(), employeeInfo.getAcademyLevel(), employeeInfo.getImagePath(), employeeInfo.getWorkGroup(),
						employeeInfo.getSocicalInsuNo(), employeeInfo.getHealthInsuNo(),
						employeeInfo.getPercentSocicalInsu() };
				jdbcTmpl.update(sql, params);
			}

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}
	
	/**
	 * Get employees from DB
	 * 
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getAllEmployees() {

		String sql = hr.getProperty("GET_ALL_EMPLOYEES").toString();
		log.info("GET_ALL_EMPLOYEES query: " + sql);
		Object[] params = new Object[] {};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sql, params, mapper);

		return list;
	}
	
	/**
	 * Get employees from DB
	 * 
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployees() {

		String sql = hr.getProperty("GET_EMPLOYEES").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES query: " + sqlUnicode);
		Object[] params = new Object[] {};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sqlUnicode, params, mapper);

		return list;
	}
		
	/**
	 * Get work group from DB
	 * 
	 * @return List of work group
	 * @throws Exception
	 */
	public List<WorkGroup> getWorkGroups() {

		String sql = hr.getProperty("GET_ALL_WORK_GROUP").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_ALL_WORK_GROUP query: " + sqlUnicode);
		WorkGroupMapper mapper = new WorkGroupMapper();

		List<WorkGroup> list = jdbcTmpl.query(sqlUnicode, mapper);

		return list;
	}
	
	/**
	 * Get employees from DB that not create for salary info 
	 * 
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployeesForInsertSalary() {

		String sql = hr.getProperty("GET_EMPLOYEES_NO_SALARY_INFO").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES_NO_SALARY_INFO query: " + sqlUnicode);	
		EmployeeMapper mapper = new EmployeeMapper();
		List<EmployeeInfo> list = jdbcTmpl.query(sqlUnicode, mapper);

		return list;
	}
	
	/**
	 * Get employees from DB that not create for insurance info 
	 * 
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployeesForInsertInsurrance() {
		String sql = hr.getProperty("GET_EMPLOYEES_NO_INSURANCE").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES_NO_INSURANCE query: " + sqlUnicode);
		Object[] params = new Object[] {};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sqlUnicode, params, mapper);

		return list;
	}
	
	/**
	 * Get employees from DB that not create for salary info 
	 * @param dept
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployeesForInsertSalaryByGroup(String group) {

		String sql = hr.getProperty("GET_EMPLOYEES_NO_SALARY_INFO_BY_WORK_GROUP").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES_NO_SALARY_INFO_BY_WORK_GROUP query: " + sqlUnicode);
		Object[] params = new Object[] {"%" + group + "%"};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sqlUnicode, params, mapper);

		return list;
	}
	
	/**
	 * Get a map of work status
	 * @return
	 */
	public Map<String, String> getWorkStatusMap(){
		Map<String, String> workStatusMap = new LinkedHashMap<String, String>();
		String sql = hr.getProperty("GET_WORK_STATUS");
		return jdbcTmpl.query(sql, new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					workStatusMap.put(rs.getString("STATUS_ID"), rs.getString("STATUS_NAME"));					
				}
				return workStatusMap;
			}
		});		
	}
	
	/**
	 * Get employees have birthday of quarter from DB
	 * 
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployeesBirth(int quarter) {

		String sql = hr.getProperty("GET_MEMBER_QUARTER_BIRTHDAY").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_MEMBER_QUARTER_BIRTHDAY query: " + sqlUnicode);
		Object[] params = new Object[] {quarter};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sqlUnicode, params, mapper);

		return list;

	}
	
	/**
	 * Get employees from DB
	 * @param department
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployeesByDepartment(String department) {

		String sql = hr.getProperty("GET_EMPLOYEES_BY_DEPARTMENT").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES_BY_DEPARTMENT query: " + sqlUnicode);
		Object[] params = new Object[] {department};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sqlUnicode, params, mapper);

		return list;
	}
	
	/**
	 * Get employees from DB
	 * @param department
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployeesByGroup(String group) {

		String sql = hr.getProperty("GET_EMPLOYEES_BY_GROUP").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES_BY_GROUP query: " + sqlUnicode);
		Object[] params = new Object[] {group};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sqlUnicode, params, mapper);

		return list;
	}
	
	/**
	 * Get employees id from DB
	 * @param department
	 * @return List of employee
	 * @throws Exception
	 */
	public List<Integer> getEmployeesIdByDepartment(String department) {

		String sql = hr.getProperty("GET_EMPLOYEES_ID_BY_DEPARTMENT").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES_ID_BY_DEPARTMENT query: " + sqlUnicode);
		Object[] params = new Object[] {department};		

		List<Integer> list = jdbcTmpl.queryForList(sqlUnicode, params, Integer.class);

		return list;
	}
	
	
	/**
	 * Get employees id from DB
	 * @param department
	 * @return List of employee
	 * @throws Exception
	 */
	public List<Integer> getEmployeesIdByGroup(String group) {

		String sql = hr.getProperty("GET_EMPLOYEES_ID_BY_WORK_GROUP").toString();
		String sqlUnicode = "";
		try {
			byte[] ptext = sql.getBytes(ISO_8859_1); 
			sqlUnicode = new String(ptext, UTF_8); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		log.info("GET_EMPLOYEES_ID_BY_WORK_GROUP query: " + sqlUnicode);
		Object[] params = new Object[] {"%" + group + "%"};		

		List<Integer> list = jdbcTmpl.queryForList(sqlUnicode, params, Integer.class);

		return list;
	}
	
	/**
	 * Get employees from DB
	 * @param search value
	 * @return List of employee
	 * @throws Exception
	 */
	public List<EmployeeInfo> getEmployeesBySearch(String value) {

		String sql = hr.getProperty("GET_EMPLOYEE_BY_SEARCH").toString();
		log.info("GET_EMPLOYEE_BY_SEARCH query: " + sql);
		value = "%" + value + "%";
		Object[] params = new Object[] {value, value, value, value, value, value, value};
		EmployeeMapper mapper = new EmployeeMapper();

		List<EmployeeInfo> list = jdbcTmpl.query(sql, params, mapper);

		return list;
	}
}
