package com.idi.hr.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.idi.hr.bean.Salary;
import com.idi.hr.bean.Salary4List;
import com.idi.hr.bean.SalaryDetail;
import com.idi.hr.bean.SalaryReport;
import com.idi.hr.bean.SalaryReportPerEmployee;
import com.idi.hr.common.PropertiesManager;
import com.idi.hr.mapper.Salary4ListMapper;
import com.idi.hr.mapper.SalaryDetailMapper;
import com.idi.hr.mapper.SalaryMapper;
import com.idi.hr.mapper.SalaryReportMapper;
import com.idi.hr.mapper.SalaryReportPerEmployeeMapper;

public class SalaryDAO extends JdbcDaoSupport {

	private static final Logger log = Logger.getLogger(SalaryDAO.class.getName());

	private JdbcTemplate jdbcTmpl;

	public JdbcTemplate getJdbcTmpl() {
		return jdbcTmpl;
	}

	public void setJdbcTmpl(JdbcTemplate jdbcTmpl) {
		this.jdbcTmpl = jdbcTmpl;
	}

	@Autowired
	public SalaryDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	PropertiesManager hr = new PropertiesManager("cabecohr.properties");

	/**
	 * Get list Salary from DB
	 * 
	 * @return List of Salary
	 * @throws Exception
	 */
	public List<Salary> getSalarys() {

		String sql = hr.getProperty("GET_LIST_SALARY_INFO").toString();
		log.info("GET_LIST_SALARY_INFO query: " + sql);
		SalaryMapper mapper = new SalaryMapper();

		List<Salary> list = jdbcTmpl.query(sql, mapper);
		return list;
	}

	/**
	 * Get list Salary by department from DB
	 * 
	 * @param dept
	 * @return List of Salary
	 * @throws Exception
	 */
	public List<Salary> getSalarysByDepartment(String dept) {

		String sql = hr.getProperty("GET_LIST_SALARY_INFO_BY_DEPARTMENT").toString();
		log.info("GET_LIST_SALARY_INFO_BY_DEPARTMENT query: " + sql);
		SalaryMapper mapper = new SalaryMapper();
		//System.err.println("dept in dao: " + dept);
		Object[] params = new Object[] { dept };
		List<Salary> list = jdbcTmpl.query(sql, params, mapper);
		return list;
	}	

	/**
	 * Get list Salary by department from DB
	 * 
	 * @param dept
	 * @return List of Salary
	 * @throws Exception
	 */
	public List<Salary4List> getSalarysByGoupAndMonth(String group, String month, String year) {

		String sql = hr.getProperty("GET_LIST_SALARY_INFO_BY_WORK_GROUP_AND_MONTH").toString();
		log.info("GET_LIST_SALARY_INFO_BY_WORK_GROUP_AND_MONTH query: " + sql);
		Salary4ListMapper mapper = new Salary4ListMapper();
		Object[] params = new Object[] { group, month, year };
		List<Salary4List> list = jdbcTmpl.query(sql, params, mapper);
		System.out.println("Work group list dao = " + group +"|" + month + "|" + year + "|" + list.size());
		return list;
	}
	
	/**
	 * get Salary by employeeId
	 * 
	 * @param employeeId
	 * @return Salary object
	 */
	public Salary getSalary(int employeeId) {

		String sql = hr.get("GET_SALARY_INFO").toString();
		log.info("GET_SALARY_INFO query: " + sql);
		Object[] params = new Object[] { employeeId };

		SalaryMapper mapper = new SalaryMapper();

		Salary salary = jdbcTmpl.queryForObject(sql, params, mapper);

		return salary;
	}

	/**
	 * Insert a Salary into database
	 * 
	 * @param Salary
	 */
	public void insertSalary(Salary salary) throws Exception {
		try {

			log.info("Thêm mới thông tin lương của nhân viên ....");
			String sql = hr.getProperty("INSERT_SALARY_INFO").toString();
			log.info("INSERT_SALARY_INFO query: " + sql);
			Object[] params = new Object[] { salary.getEmployeeId(), salary.getSalary().replaceAll(",", ""), 
					salary.getConstSalary(), salary.getBankNo(), salary.getBankName(), salary.getBankBranch(), salary.getDesc() };
			jdbcTmpl.update(sql, params);

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/**
	 * Update a Salary into database
	 * 
	 * @param Salary
	 */
	public void updateSalary(Salary salary) throws Exception {
		try {
			log.info("Cập nhật thông tin lương của NV có mã NV: " + salary.getEmployeeId() + " ....");
			// update
			String sql = hr.getProperty("UPDATE_SALARY_INFO").toString();
			log.info("UPDATE_SALARY_INFO query: " + sql);
			Object[] params = new Object[] { salary.getSalary(), salary.getConstSalary(), salary.getBankNo(),
					salary.getBankName(), salary.getBankBranch(), salary.getDesc(), salary.getEmployeeId() };
			jdbcTmpl.update(sql, params);

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	// ------------------------------Salary for moth -------------------------//
	/**
	 * Get list SalaryDetail from DB
	 * 
	 * @return List of SalaryDetail
	 * @param employeeId
	 * @throws Exception
	 */
	public List<SalaryDetail> getSalaryDetails(int employeeId) {
		String sql = hr.getProperty("GET_LIST_SALARY_DETAIL").toString();
		log.info("GET_LIST_SALARY_DETAIL query: " + sql);
		Object[] params = new Object[] { employeeId };
		SalaryDetailMapper mapper = new SalaryDetailMapper();

		List<SalaryDetail> list = jdbcTmpl.query(sql, params, mapper);
		return list;
	}

	/**
	 * get SalaryDetail detail for month
	 * 
	 * @param employeeId, month, year
	 * @return SalaryDetail object
	 */
	public SalaryDetail getSalaryDetail(int employeeId, int month, int year) {

		SalaryDetail salaryDetail = null;
		if (month > 0 && year > 0) {
			String sql = hr.get("GET_SALARY_DETAIL").toString();
			log.info("GET_SALARY_DETAIL query: " + sql);
			Object[] params = new Object[] { employeeId, month, year };
			SalaryDetailMapper mapper = new SalaryDetailMapper();
			salaryDetail = jdbcTmpl.queryForObject(sql, params, mapper);
		} else {
			String sql = hr.get("GET_SALARY_FOR_INSERT").toString();
			log.info("GET_SALARY_FOR_INSERT query: " + sql);
			Object[] params = new Object[] { employeeId };
			SalaryDetailMapper mapper = new SalaryDetailMapper();
			salaryDetail = jdbcTmpl.queryForObject(sql, params, mapper);
		}
		return salaryDetail;
	}

	/**
	 * 
	 * @param employeeId
	 * @param month
	 * @param year
	 * @return
	 */
	public SalaryReport getSalaryReport(int employeeId, String month, String year, String dept, String group) {

		SalaryReport salaryReport = null;
		String sql = hr.get("GET_SUMMARY_SALARY").toString();
		if (month != null && month.length() > 0 && Integer.parseInt(month) > 0) {
			sql = sql + " AND MONTH=" + month;			
		}
		if (employeeId > 0) {
			sql = sql + " AND EMPLOYEE_ID=" + employeeId;
		}
		if(dept != null && dept.length() > 0 && !dept.equalsIgnoreCase("all"))
			sql = sql.replaceAll("%DEPT%", " AND E.DEPARTMENT= '" + dept + "'");
		else
			sql = sql.replaceAll("%DEPT%", " ");
		
		if(group != null && group.length() > 0 && !group.equalsIgnoreCase("all"))
			sql = sql.replaceAll("%GROUP%", " AND E.WORK_GROUP= '" + group + "'");
		else
			sql = sql.replaceAll("%GROUP%", " ");
		
		
		log.info("GET_SUMMARY_SALARY query: " + sql);
		
		Object[] params = new Object[] { year };
		SalaryReportMapper mapper = new SalaryReportMapper();
		salaryReport = jdbcTmpl.queryForObject(sql, params, mapper);

		return salaryReport;
	}

	/**
	 * 
	 * @param year
	 * @return
	 */
	public List<SalaryReportPerEmployee> getSalaryReportDetail(String year, String dept, String group) {
		System.err.println("dept: " + dept);
		String sql = hr.get("GET_SUMMARY_SALARY_DETAIL_FOR_YEAR").toString();
		
		if(dept != null && dept.length() > 0 && !dept.equalsIgnoreCase("all"))
			sql = sql.replaceAll("%DEPT%", " AND E.DEPARTMENT= '" + dept + "'");
		else
			sql = sql.replaceAll("%DEPT%", " ");
		
		if(dept != null && dept.length() > 0 && !dept.equalsIgnoreCase("all"))
			sql = sql.replaceAll("%DEPT%", " AND E.DEPARTMENT= '" + dept + "'");
		else
			sql = sql.replaceAll("%DEPT%", " ");
		
		if(group != null && group.length() > 0 && !group.equalsIgnoreCase("all"))
			sql = sql.replaceAll("%GROUP%", " AND E.WORK_GROUP= '" + group + "'");
		else
			sql = sql.replaceAll("%GROUP%", " ");
		
		log.info("GET_SUMMARY_SALARY_DETAIL_FOR_YEAR query: " + sql);
		
		Object[] params = new Object[] { year };
		SalaryReportPerEmployeeMapper mapper = new SalaryReportPerEmployeeMapper();
		List<SalaryReportPerEmployee> list = jdbcTmpl.query(sql, params, mapper); 

		return list;
	}
	
	/**
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public List<SalaryDetail> getSalaryReportDetail(String month, String year, String dept, String group) {
		System.err.println("dept: " + dept);
		String sql = hr.get("GET_SUMMARY_SALARY_DETAIL").toString();
		if(dept != null && dept.length() > 0 && !dept.equalsIgnoreCase("all"))
			sql = sql.replaceAll("%DEPT%", " AND E.DEPARTMENT= '" + dept +"'");
		else
			sql = sql.replaceAll("%DEPT%", " ");
		
		if(group != null && group.length() > 0 && !group.equalsIgnoreCase("all"))
			sql = sql.replaceAll("%GROUP%", " AND E.WORK_GROUP= '" + group + "'");
		else
			sql = sql.replaceAll("%GROUP%", " ");
		
		log.info("GET_SUMMARY_SALARY_DETAIL query: " + sql);
		
		Object[] params = new Object[] { month, year };
		SalaryDetailMapper mapper = new SalaryDetailMapper();
		List<SalaryDetail> list = jdbcTmpl.query(sql, params, mapper); 

		return list;
	}
	
	/**
	 * Insert a SalaryDetail into database
	 * 
	 * @param SalaryDetail
	 */
	public void insertSalaryDetail(SalaryDetail salaryDetail) throws Exception {
		try {

			log.info("Thêm thông tin chi tiết lương tháng của nhân viên....");
			String sql = hr.getProperty("INSERT_SALARY_DETAIL").toString();
			log.info("INSERT_SALARY_DETAIL query: " + sql);
			//System.err.println("salary " + salaryDetail.getSalary());
			// Tính lương thực nhận
			/*float finalSalary = 0;
			if(salaryDetail.getSalaryForWorkedDay() != null) {
				finalSalary = Float.valueOf(salaryDetail.getSalaryForWorkedDay());
			}else {
				if(salaryDetail.getBasicSalary() != null && salaryDetail.getBasicSalary().length() > 0)
					finalSalary = Float.valueOf(salaryDetail.getBasicSalary());
				else
					finalSalary = Float.valueOf(salaryDetail.getSalary())*Float.valueOf(hr.getProperty("BASIC_SALARY"));
			}
			if(salaryDetail.getWorkComplete() >= 0) {
				finalSalary = finalSalary*salaryDetail.getWorkComplete()/100;
				//System.err.println(8000000*120/100);
			}
			
			//get from other table
			float r = 1;
			float rSalary = Float.valueOf(salaryDetail.getWorkedDay())*r;
			rSalary = rSalary*100/100;
			salaryDetail.setrSalary(String.valueOf(rSalary));
			
			// Tăng
			float salaryPerHour = 0;
			if (salaryDetail.getSalaryPerHour() > 0)
				salaryPerHour = salaryDetail.getSalaryPerHour();
			if (salaryDetail.getBounus() != null && salaryDetail.getBounus().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getBounus().replaceAll(",", ""));
				salaryDetail.setBounus(salaryDetail.getBounus().replaceAll(",", ""));
			}
			if (salaryDetail.getSubsidize() != null && salaryDetail.getSubsidize().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubsidize().replaceAll(",", ""));
				salaryDetail.setSubsidize(salaryDetail.getSubsidize().replaceAll(",", ""));
			}
			if (salaryDetail.getOverTimeN() != null && salaryDetail.getOverTimeN().length() > 0) {
				finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeN()) * (float) 1.5;
			}
			if (salaryDetail.getOverTimeW() != null && salaryDetail.getOverTimeW().length() > 0) {
				finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeW()) * 2;
			}
			if (salaryDetail.getOverTimeH() != null && salaryDetail.getOverTimeH().length() > 0) {
				finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeH()) * 3;
			}
			if (salaryDetail.getOther() != null && salaryDetail.getOther().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getOther().replaceAll(",", ""));
				salaryDetail.setOther(salaryDetail.getOther().replaceAll(",", ""));
			}
			if (salaryDetail.getSubLunch() != null && salaryDetail.getSubLunch().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubLunch().replaceAll(",", ""));
				salaryDetail.setSubLunch(salaryDetail.getSubLunch().replaceAll(",", ""));
			}
			if (salaryDetail.getSubPhone() != null && salaryDetail.getSubPhone().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubPhone().replaceAll(",", ""));
				salaryDetail.setSubPhone(salaryDetail.getSubPhone().replaceAll(",", ""));
			}
			if (salaryDetail.getSubGas() != null && salaryDetail.getSubGas().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubGas().replaceAll(",", ""));
				salaryDetail.setSubGas(salaryDetail.getSubGas().replaceAll(",", ""));
			}
			if (salaryDetail.getOverWork() != null && salaryDetail.getOverWork().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getOverWork().replaceAll(",", ""));
				salaryDetail.setOverWork(salaryDetail.getOverWork().replaceAll(",", ""));
			}
			if (salaryDetail.getMaintainDay() != null && salaryDetail.getMaintainDay().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getMaintainDay().replaceAll(",", ""));
				salaryDetail.setMaintainDay(salaryDetail.getMaintainDay().replaceAll(",", ""));
			}	
			if(rSalary > 0) {
				finalSalary = finalSalary + rSalary;
			}
			salaryDetail.setTotalIncome(String.valueOf(finalSalary));
			
			// Giảm
			if (salaryDetail.getArrears() != null && salaryDetail.getArrears().length() > 0) {
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getArrears().replaceAll(",", ""));
				salaryDetail.setArrears(salaryDetail.getArrears().replaceAll(",", ""));
			}if (salaryDetail.getTaxPersonal() != null && salaryDetail.getTaxPersonal().length() > 0) {
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getTaxPersonal().replaceAll(",", ""));
				salaryDetail.setTaxPersonal(salaryDetail.getTaxPersonal().replaceAll(",", ""));
			}if (salaryDetail.getAdvancePayed() != null && salaryDetail.getAdvancePayed().length() > 0) {
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getAdvancePayed().replaceAll(",", ""));
				salaryDetail.setAdvancePayed(salaryDetail.getAdvancePayed().replaceAll(",", ""));
			}if (salaryDetail.getSalaryInsurance() != null && salaryDetail.getSalaryInsurance().length() > 0)
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getSalaryInsurance()) * (float) 10.5 / 100;
			
			salaryDetail.setTotalReduce(String.valueOf(Float.valueOf(salaryDetail.getTotalIncome()) - finalSalary));

			salaryDetail.setFinalSalary(String.valueOf(finalSalary));
			*/
			
			if(salaryDetail.getSubInsurance() != null && salaryDetail.getSubInsurance().trim().length() > 0) {
				salaryDetail.setSubInsurance(salaryDetail.getSubInsurance().replaceAll(",", ""));
			}	
			//update ... lay salary o bang salary info sang bang salary detail lam basic salary
			Object[] params = new Object[] { salaryDetail.getEmployeeId(), salaryDetail.getUnionFee(),
					salaryDetail.getSubInsurance(), salaryDetail.getWorkedTime(), salaryDetail.getWorkedTimePrice(),
					salaryDetail.getOverTimeSalary(), salaryDetail.getBounus(), salaryDetail.getMaintainDay(), salaryDetail.getSubsidize(),
					salaryDetail.getSubLunch(), salaryDetail.getSubPhone(), salaryDetail.getSubGas(), salaryDetail.getSubSkill(),
					salaryDetail.getOverWork(), salaryDetail.getAdvancePayed(), salaryDetail.getTaxPersonal(),
					salaryDetail.getBasicSalary(), salaryDetail.getTotalIncome(), salaryDetail.getTotalReduce(),
					salaryDetail.getFinalSalary(), salaryDetail.getMonth(), salaryDetail.getYear(), salaryDetail.getDesc(),
					salaryDetail.getPayedInsurance(), salaryDetail.getWorkComplete(), salaryDetail.getWorkedDay(),
					salaryDetail.getOther(), salaryDetail.getArrears(), salaryDetail.getPayStatus(), salaryDetail.getrSalary(), 
					salaryDetail.getSaProduct(), salaryDetail.getSaTime() };
			jdbcTmpl.update(sql, params);

		} catch (Exception e) {
			try {
				//e.printStackTrace();
				log.warn("Insert has error ... trying to update");
				updateSalaryDetail(salaryDetail);
			} catch (Exception ex) {
				log.error(ex, ex);
				throw ex;
			}
		}
	}

	/**
	 * Update a SalaryDetail into database
	 * 
	 * @param SalaryDetail
	 */
	public void updateSalaryDetail(SalaryDetail salaryDetail) throws Exception {
		try {

			log.info("Cập nhật thông tin chi tiết lương tháng " + salaryDetail.getMonth() + ", "
					+ salaryDetail.getYear() + ", mã NV " + salaryDetail.getEmployeeId());
			String sql = hr.getProperty("UPDATE_SALARY_DETAIL").toString();
			log.info("UPDATE_SALARY_DETAIL query: " + sql);

			/*
			// Tính lương thực nhận
			float finalSalary = 0;
			if(salaryDetail.getSalaryForWorkedDay() != null) {
				finalSalary = Float.valueOf(salaryDetail.getSalaryForWorkedDay());
			}else {
				if(salaryDetail.getBasicSalary() != null && salaryDetail.getBasicSalary().length() > 0)
					finalSalary = Float.valueOf(salaryDetail.getBasicSalary());
				else
				 finalSalary = Float.valueOf(salaryDetail.getSalary())*Float.valueOf(hr.getProperty("BASIC_SALARY"));				 
			}
			
			if(salaryDetail.getWorkComplete() >= 0) {
				finalSalary = finalSalary*salaryDetail.getWorkComplete()/100;				
			}
			//get from other table --> luong dieu tiet
			float r = 1;
			float rSalary = Float.valueOf(salaryDetail.getWorkedDay())*r;
			rSalary = rSalary*100/100;
			salaryDetail.setrSalary(String.valueOf(rSalary));
			
			// Tang
			float salaryPerHour = 0;
			if (salaryDetail.getSalaryPerHour() > 0)
				salaryPerHour = salaryDetail.getSalaryPerHour();
			if (salaryDetail.getBounus() != null && salaryDetail.getBounus().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getBounus().replaceAll(",", ""));
				salaryDetail.setBounus(salaryDetail.getBounus().replaceAll(",", ""));
			}	
			if (salaryDetail.getSubsidize() != null && salaryDetail.getSubsidize().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubsidize().replaceAll(",", ""));
				salaryDetail.setSubsidize(salaryDetail.getSubsidize().replaceAll(",", ""));
			}	
			if (salaryDetail.getOverTimeN() != null && salaryDetail.getOverTimeN().length() > 0) {
				finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeN()) * (float) 1.5;
			}
			if (salaryDetail.getOverTimeW() != null && salaryDetail.getOverTimeW().length() > 0) {
				finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeW()) * 2;
			}
			if (salaryDetail.getOverTimeH() != null && salaryDetail.getOverTimeH().length() > 0) {
				finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeH()) * 3;
			}
			if (salaryDetail.getOther() != null && salaryDetail.getOther().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getOther().replaceAll(",", ""));
				salaryDetail.setOther(salaryDetail.getOther().replaceAll(",", ""));
			}
			if (salaryDetail.getSubLunch() != null && salaryDetail.getSubLunch().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubLunch().replaceAll(",", ""));
				salaryDetail.setSubLunch(salaryDetail.getSubLunch().replaceAll(",", ""));
			}
			if (salaryDetail.getSubPhone() != null && salaryDetail.getSubPhone().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubPhone().replaceAll(",", ""));
				salaryDetail.setSubPhone(salaryDetail.getSubPhone().replaceAll(",", ""));
			}
			if (salaryDetail.getSubGas() != null && salaryDetail.getSubGas().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubGas().replaceAll(",", ""));
				salaryDetail.setSubGas(salaryDetail.getSubGas().replaceAll(",", ""));
			}
			if (salaryDetail.getOverWork() != null && salaryDetail.getOverWork().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getOverWork().replaceAll(",", ""));
				salaryDetail.setOverWork(salaryDetail.getOverWork().replaceAll(",", ""));
			}
			if (salaryDetail.getMaintainDay() != null && salaryDetail.getMaintainDay().length() > 0) {
				finalSalary = finalSalary + Float.valueOf(salaryDetail.getMaintainDay().replaceAll(",", ""));
				salaryDetail.setMaintainDay(salaryDetail.getMaintainDay().replaceAll(",", ""));
			}	
			if(rSalary > 0) {
				finalSalary = finalSalary + rSalary;
			}
			salaryDetail.setTotalIncome(String.valueOf(finalSalary));
			
			// Giảm
			if (salaryDetail.getArrears() != null && salaryDetail.getArrears().length() > 0) {
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getArrears().replaceAll(",", ""));
				salaryDetail.setArrears(salaryDetail.getArrears().replaceAll(",", ""));
			}	
			if (salaryDetail.getTaxPersonal() != null && salaryDetail.getTaxPersonal().length() > 0) {
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getTaxPersonal().replaceAll(",", ""));
				salaryDetail.setTaxPersonal(salaryDetail.getTaxPersonal().replaceAll(",", ""));
			}	
			if (salaryDetail.getAdvancePayed() != null && salaryDetail.getAdvancePayed().length() > 0) {
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getAdvancePayed().replaceAll(",", ""));
				salaryDetail.setAdvancePayed(salaryDetail.getAdvancePayed().replaceAll(",", ""));
			}	
			if (salaryDetail.getSalaryInsurance() != null && salaryDetail.getSalaryInsurance().length() > 0) {
				//System.err.println(salaryDetail.getSalaryInsurance());
				finalSalary = finalSalary - Float.valueOf(salaryDetail.getSalaryInsurance()) * (float) 10.5 / 100;
			}
			salaryDetail.setTotalReduce(String.valueOf(Float.valueOf(salaryDetail.getTotalIncome()) - finalSalary));
			
			salaryDetail.setFinalSalary(String.valueOf(finalSalary));
			*/
			//System.err.println("year " + salaryDetail.getYear());
			//System.err.println("Luong thuc nhan " + finalSalary);
			
			if(salaryDetail.getSubInsurance() != null && salaryDetail.getSubInsurance().trim().length() > 0) {
				salaryDetail.setSubInsurance(salaryDetail.getSubInsurance().replaceAll(",", ""));
			}
			Object[] params = new Object[] { salaryDetail.getUnionFee(), salaryDetail.getSubInsurance(),
					salaryDetail.getWorkedTime(), salaryDetail.getWorkedTimePrice(), salaryDetail.getOverTimeSalary(), salaryDetail.getBounus(),
					salaryDetail.getMaintainDay(), salaryDetail.getSubsidize(), salaryDetail.getSubLunch(),
					salaryDetail.getSubPhone(), salaryDetail.getSubGas(), salaryDetail.getSubSkill(), salaryDetail.getOverWork(),
					salaryDetail.getAdvancePayed(), salaryDetail.getTaxPersonal(), salaryDetail.getBasicSalary(), 
					salaryDetail.getTotalIncome(), salaryDetail.getTotalReduce(), salaryDetail.getFinalSalary(), salaryDetail.getDesc(), 
					salaryDetail.getPayedInsurance(), salaryDetail.getWorkComplete(), salaryDetail.getWorkedDay(), salaryDetail.getOther(),
					salaryDetail.getArrears(), salaryDetail.getPayStatus(),salaryDetail.getrSalary(), salaryDetail.getSaProduct(), salaryDetail.getSaTime(),
					salaryDetail.getEmployeeId(), salaryDetail.getMonth(), salaryDetail.getYear() };
			jdbcTmpl.update(sql, params);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e, e);
			throw e;
		}
	}

	/**
	 * Update total salary take home
	 * 
	 * @param finalSalary,
	 *            month, year, employeeId
	 */
	public void updateSalaryTakeHome(String finalSalary, int month, int year, int employeeId) throws Exception {
		try {

			log.info("Cập nhật thông tin lương thực nhận tháng " + month + ", " + year + ", mã NV " + employeeId);
			// update
			String sql = hr.getProperty("UPDATE_SALARY_DETAIL_FINAL").toString();
			log.info("UPDATE_SALARY_DETAIL_FINAL query: " + sql);
			Object[] params = new Object[] { finalSalary, employeeId, month, year };
			jdbcTmpl.update(sql, params);

		} catch (Exception e) {
			log.error(e, e);
			throw e;
		}
	}

	/**
	 * Count member in department filled worked day
	 * @param month, year, department 
	 * @return number
	 */
	public int countMembers( int month, int year, String department) {

		String sql = hr.get("GET_COUNT_SALARY_FILLED").toString();
		log.info("GET_COUNT_SALARY_FILLED query: " + sql);
		Object[] params = new Object[] { month, year, department };
		
		String numberEmployee = jdbcTmpl.queryForObject(sql, String.class, params);
		
		return Integer.parseInt(numberEmployee);
	}
}
