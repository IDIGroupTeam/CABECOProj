package com.idi.hr.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.idi.hr.bean.SalaryRe;
import com.idi.hr.common.PropertiesManager;
import com.idi.hr.mapper.SalaryReMapper;

public class SalaryReDAO extends JdbcDaoSupport {

	private static final Logger log = Logger.getLogger(SalaryReDAO.class.getName());
	private JdbcTemplate jdbcTmpl;

	public JdbcTemplate getJdbcTmpl() {
		return jdbcTmpl;
	}

	public void setJdbcTmpl(JdbcTemplate jdbcTmpl) {
		this.jdbcTmpl = jdbcTmpl;
	}

	@Autowired
	public SalaryReDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	PropertiesManager hr = new PropertiesManager("cabecohr.properties");

	/**
	 * Get list salary re from DB
	 * 
	 * @return List of salary re
	 * @throws Exception
	 */
	public List<SalaryRe> getListSalaryRe() {
		String sql = hr.getProperty("GET_LIST_SALARY_RE").toString();
		log.info("GET_LIST_SALARY_RE query: " + sql);
		SalaryReMapper mapper = new SalaryReMapper();
		List<SalaryRe> list = jdbcTmpl.query(sql, mapper);
		return list;
	}
	
	/**
	 * 
	 * @param department
	 * @param month
	 * @param year
	 * @return
	 */
	public SalaryRe getSalaryRe(String group, int month, int year) {
		SalaryRe salaryRe = new SalaryRe();
		try {
			String sql = hr.get("GET_SALARY_RE").toString();
			log.info("GET_SALARY_RE query: " + sql);
			Object[] params = new Object[] { month, year, group };
			//System.out.println(month + "|" + year ); 
			SalaryReMapper mapper = new SalaryReMapper();	
			salaryRe = jdbcTmpl.queryForObject(sql, params, mapper);
		}catch(Exception e) {
			return salaryRe;
			//e.printStackTrace();
		}
		return salaryRe;
	}

	/**
	 * 
	 * @param salaryRe
	 * @return
	 * @throws Exception
	 */
	public int insertSalaryRe(SalaryRe salaryRe) throws Exception {
		int rowInsert = 0;
		try {			
			log.info("Insert new productSold ....");
			String sql = hr.getProperty("INSERT_SALARY_RE").toString();
			log.info("INSERT_SALARY_RE query: " + sql);
			Object[] params = new Object[] {salaryRe.getMonth(), salaryRe.getYear(), salaryRe.getGroup(), salaryRe.getValue(), salaryRe.getDes()};
			rowInsert = jdbcTmpl.update(sql, params);
		} catch (Exception e) {
			log.error(e, e);
			return 0;
		}
		return rowInsert;
	}

	/**
	 * 
	 * @param SalaryRe
	 * @return
	 * @throws Exception
	 */
	public int updateSalaryRe(SalaryRe salaryRe) throws Exception {
		int rowUpdate = 0;
		try {
			log.info("Update salaryRe ...");
			String sql = hr.getProperty("UPDATE_SALARY_RE").toString();
			log.info("UPDATE_SALARY_RE query: " + sql);
			Object[] params = new Object[] {salaryRe.getValue(), salaryRe.getDes(), salaryRe.getMonth(), salaryRe.getYear(), salaryRe.getGroup()};
			//System.err.println("Update salaryRe: " + salaryRe.getValue() + "|"+ salaryRe.getDes() +"|"+ salaryRe.getMonth()+"|"+  salaryRe.getYear()+"|"+  salaryRe.getGroup());
			rowUpdate = jdbcTmpl.update(sql, params);
		} catch (Exception e) {
			log.error(e, e);
			return 0;
		}
		return rowUpdate;
	}	
}
