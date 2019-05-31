package com.idi.hr.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idi.hr.bean.Department;
import com.idi.hr.bean.EmployeeInfo;
import com.idi.hr.bean.LeaveReport;
import com.idi.hr.bean.Product;
import com.idi.hr.bean.ProductSold;
import com.idi.hr.bean.Salary;
import com.idi.hr.bean.SalaryDetail;
import com.idi.hr.bean.SalaryReport;
import com.idi.hr.bean.SalaryReportPerEmployee;
import com.idi.hr.bean.WorkingDay;
import com.idi.hr.common.PropertiesManager;
import com.idi.hr.common.Utils;
import com.idi.hr.dao.DepartmentDAO;
import com.idi.hr.dao.EmployeeDAO;
import com.idi.hr.dao.ProductSoldDAO;
import com.idi.hr.dao.SalaryDAO;
import com.idi.hr.dao.WorkingDayDAO;
import com.idi.hr.form.SalaryForm;

@Controller
public class SalaryController {
	private static final Logger log = Logger.getLogger(SalaryController.class.getName());

	@Autowired
	private SalaryDAO salaryDAO;

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private WorkingDayDAO workingDayDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Autowired
	private ProductSoldDAO productSoldDAO;

	PropertiesManager hr = new PropertiesManager("cabecohr.properties");

	@RequestMapping(value = { "/salary/" })
	public String listSalarys(Model model, @ModelAttribute("salaryForm") SalaryForm form) throws Exception {
		try {
			// Paging:
			// Number records of a Page: Default: 25
			// Page Index: Default: 1
			// Total records
			// Total of page
			if (form.getNumberRecordsOfPage() == 0) {
				form.setNumberRecordsOfPage(25);
			}
			if (form.getPageIndex() == 0) {
				form.setPageIndex(1);
			}

			List<Salary> list = salaryDAO.getSalarys();

			form.setTotalRecords(list.size());

			int totalPages = form.getTotalRecords() % form.getNumberRecordsOfPage() > 0
					? form.getTotalRecords() / form.getNumberRecordsOfPage() + 1
					: form.getTotalRecords() / form.getNumberRecordsOfPage();
			form.setTotalPages(totalPages);

			List<Salary> listSalaryForPage = new ArrayList<Salary>();

			if (form.getPageIndex() < totalPages) {
				if (form.getPageIndex() == 1) {
					for (int i = 0; i < form.getNumberRecordsOfPage(); i++) {
						Salary salary = new Salary();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				} else if (form.getPageIndex() > 1) {
					for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form.getPageIndex()
							* form.getNumberRecordsOfPage(); i++) {
						Salary salary = new Salary();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				}
			} else if (form.getPageIndex() == totalPages) {
				for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
						.getTotalRecords(); i++) {
					Salary salary = new Salary();
					salary = list.get(i);
					listSalaryForPage.add(salary);
				}
			}
			model.addAttribute("salaryForm", form);
			model.addAttribute("salarys", listSalaryForPage);
			model.addAttribute("formTitle", "Danh sách lương của nhân viên ");
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "listSalaryInfo";
	}
	
	@RequestMapping(value = { "/salary/listSalarysByDepartment" })
	public String listSalarysByDepartment(Model model, @ModelAttribute("salaryForm") SalaryForm form) throws Exception {
		try {
			// Paging:
			// Number records of a Page: Default: 25
			// Page Index: Default: 1
			// Total records
			// Total of page
			if (form.getNumberRecordsOfPage() == 0) {
				form.setNumberRecordsOfPage(25);
			}
			if (form.getPageIndex() == 0) {
				form.setPageIndex(1);
			}
			//System.err.println("deprt list = " + form.getDepartment());
			List<Salary> list = salaryDAO.getSalarysByDepartment(form.getDepartment());

			form.setTotalRecords(list.size());

			int totalPages = form.getTotalRecords() % form.getNumberRecordsOfPage() > 0
					? form.getTotalRecords() / form.getNumberRecordsOfPage() + 1
					: form.getTotalRecords() / form.getNumberRecordsOfPage();
			form.setTotalPages(totalPages);

			List<Salary> listSalaryForPage = new ArrayList<Salary>();

			if (form.getPageIndex() < totalPages) {
				if (form.getPageIndex() == 1) {
					for (int i = 0; i < form.getNumberRecordsOfPage(); i++) {
						Salary salary = new Salary();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				} else if (form.getPageIndex() > 1) {
					for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form.getPageIndex()
							* form.getNumberRecordsOfPage(); i++) {
						Salary salary = new Salary();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				}
			} else if (form.getPageIndex() == totalPages) {
				for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
						.getTotalRecords(); i++) {
					Salary salary = new Salary();
					salary = list.get(i);
					listSalaryForPage.add(salary);
				}
			}
			
			model.addAttribute("salaryForm", form);
			model.addAttribute("salarys", listSalaryForPage);
			model.addAttribute("formTitle", "Danh sách lương của nhân viên thuộc bộ phận " + departments().get(form.getDepartment()));
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "listSalaryInfo";
	}

	private Map<String, String> employees() {
		Map<String, String> employeeMap = new LinkedHashMap<String, String>();
		try {
			List<EmployeeInfo> list = employeeDAO.getEmployees();
			EmployeeInfo employee = new EmployeeInfo();
			for (int i = 0; i < list.size(); i++) {
				employee = (EmployeeInfo) list.get(i);
				Integer id = employee.getEmployeeId();
				employeeMap.put(id.toString(), employee.getFullName() + ", phòng " + employee.getDepartment());
			}

		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return employeeMap;
	}

	/*
	 * private Map<String, String> employeesNoInfo() { Map<String, String>
	 * employeeMap = new LinkedHashMap<String, String>(); try { List<EmployeeInfo>
	 * list = employeeDAO.getEmployeesForInsertSalary(); EmployeeInfo employee = new
	 * EmployeeInfo(); for (int i = 0; i < list.size(); i++) { employee =
	 * (EmployeeInfo) list.get(i); Integer id = employee.getEmployeeId();
	 * employeeMap.put(id.toString(), employee.getFullName() + ", phòng " +
	 * employee.getDepartment()); }
	 * 
	 * } catch (Exception e) { log.error(e, e); e.printStackTrace(); } return
	 * employeeMap; }
	 */
	
	private Map<String, String> employeesNoInfoByDept(String dept) {
		Map<String, String> employeeMap = new LinkedHashMap<String, String>();
		try {
			List<EmployeeInfo> list = employeeDAO.getEmployeesForInsertSalaryByDept(dept);
			EmployeeInfo employee = new EmployeeInfo();
			for (int i = 0; i < list.size(); i++) {
				employee = (EmployeeInfo) list.get(i);
				Integer id = employee.getEmployeeId();
				employeeMap.put(id.toString(), employee.getFullName());
			}

		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return employeeMap;
	}

	@RequestMapping(value = "/salary/insertSalary", method = RequestMethod.POST)
	public String addSalary(Model model, @ModelAttribute("salaryForm") @Validated Salary salary,
			final RedirectAttributes redirectAttributes) throws Exception{
		SalaryForm form = new SalaryForm();
		try {
			form.setDepartment(salary.getDepartment());
			salaryDAO.insertSalary(salary);
			// Add message to flash scope
			model.addAttribute("message", "Thêm thông tin lương nhân viên thành công!");

		} catch (Exception e) {
			log.error(e, e);
		}
		return listSalarysByDepartment(model, form);
	}

	@RequestMapping(value = "/salary/updateSalary", method = RequestMethod.POST)
	public String updateSalary(Model model, @ModelAttribute("salaryForm") @Validated Salary salary,
			final RedirectAttributes redirectAttributes) throws Exception{
		SalaryForm form = new SalaryForm();
		try {
			form.setDepartment(salary.getDepartment());			
			salaryDAO.updateSalary(salary);
			// Add message to flash scope
			model.addAttribute("message", "Cập nhật thông tin lương nhân viên thành công!");

		} catch (Exception e) {
			log.error(e, e);
		}
		return listSalarysByDepartment(model, form);
	}

	private String salaryForm(Model model, Salary salary) {
		model.addAttribute("salaryForm", salary);
		// get list employee id
		Map<String, String> employeeMap = this.employeesNoInfoByDept(salary.getDepartment());
		model.addAttribute("employeeMap", employeeMap);

		String actionform = "";
		if (salary.getEmployeeId() > 0) {
			model.addAttribute("formTitle", "Sửa thông tin lương nhân viên thuộc bộ phận " + departments().get(salary.getDepartment()));
			actionform = "editSalaryInfo";
		} else {
			model.addAttribute("formTitle", "Thêm mới thông tin lương nhân viên thuộc bộ phận " + departments().get(salary.getDepartment()));
			actionform = "insertSalaryInfo";
		}
		return actionform;
	}

	@RequestMapping("/salary/insertSalary")
	public String addSalary(Model model, @RequestParam(required = false, name="department") String department) {
		Salary salary = new Salary();
		salary.setConstSalary("100");
		salary.setDepartment(department);
		return this.salaryForm(model, salary);
	}

	@RequestMapping("/salary/editSalary")
	public String editSalary(Model model, @RequestParam("employeeId") int employeeId, @RequestParam(required = false, name="department") String department) {
		Salary salary = null;
		if (employeeId > 0) {
			salary = salaryDAO.getSalary(employeeId);
			salary.setDepartment(department);
			//System.err.println("he so luong: " + salary.getConstSalary());
		}
		if (salary == null) {
			return "redirect:/salary/";
		}

		return this.salaryForm(model, salary);
	}

	// ------ Tính lương thực nhận cho NV theo tháng --------//
	@RequestMapping(value = "/salary/listSalaryDetail")
	public String listSalaryDetail(Model model, @RequestParam("employeeId") int employeeId,
			@ModelAttribute("salaryForm") SalaryForm form) {
		try {

			List<SalaryDetail> list = salaryDAO.getSalaryDetails(employeeId);

			// Paging:
			// Number records of a Page: Default: 25
			// Page Index: Default: 1
			// Total records
			// Total of page
			if (form.getNumberRecordsOfPage() == 0) {
				form.setNumberRecordsOfPage(25);
			}
			if (form.getPageIndex() == 0) {
				form.setPageIndex(1);
			}

			form.setTotalRecords(list.size());

			int totalPages = form.getTotalRecords() % form.getNumberRecordsOfPage() > 0
					? form.getTotalRecords() / form.getNumberRecordsOfPage() + 1
					: form.getTotalRecords() / form.getNumberRecordsOfPage();
			form.setTotalPages(totalPages);

			List<SalaryDetail> listSalaryForPage = new ArrayList<SalaryDetail>();

			if (form.getPageIndex() < totalPages) {
				if (form.getPageIndex() == 1) {
					for (int i = 0; i < form.getNumberRecordsOfPage(); i++) {
						SalaryDetail salary = new SalaryDetail();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				} else if (form.getPageIndex() > 1) {
					for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form.getPageIndex()
							* form.getNumberRecordsOfPage(); i++) {
						SalaryDetail salary = new SalaryDetail();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				}
			} else if (form.getPageIndex() == totalPages) {
				for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
						.getTotalRecords(); i++) {
					SalaryDetail salary = new SalaryDetail();
					salary = list.get(i);
					listSalaryForPage.add(salary);
				}
			}
			model.addAttribute("salaryForm", form);
			model.addAttribute("employeeId", employeeId);
			Map<String, String> employeeMap = this.employees();
			String name = "";
			name = employeeMap.get(String.valueOf(employeeId));
			model.addAttribute("name", name);

			if (list != null && list.size() < 1) {
				model.addAttribute("message", "Chưa có thông tin lương cho nhân viên này");
				model.addAttribute("formTitle", "Danh sách lương tháng của " + name);
			} else
				model.addAttribute("formTitle", "Danh sách lương tháng của " + list.get(0).getFullName());

			model.addAttribute("salaryDetails", listSalaryForPage);

		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "listSalaryByEmployee";
	}

	@RequestMapping(value = "/salary/insertSalaryDetailForm")
	public String insertSalaryDetailForm(Model model, @RequestParam("employeeId") int employeeId,
			SalaryDetail salaryDetail) {
		try {
			Calendar now = Calendar.getInstance();
			int month = now.get(Calendar.MONTH) + 1;
			if (salaryDetail.getMonth() > 0)
				month = salaryDetail.getMonth();
			int year = now.get(Calendar.YEAR);
			if (salaryDetail.getYear() > 0)
				year = salaryDetail.getYear();
			// System.out.println("Current Month is: " + month + ", year: " + year);
			// System.out.println("tinh luong cho Month is: " + salaryDetail.getMonth() + ",
			// year: " + year);
			// SalaryDetail salaryDetail = new SalaryDetail();
			try {
				salaryDetail = salaryDAO.getSalaryDetail(employeeId, month, year);
				// return editSalaryDetailForm(model, salaryDetail);
			} catch (Exception e) {
				salaryDetail = salaryDAO.getSalaryDetail(employeeId, 0, 0);
				if (salaryDetail.getSalary() != null && salaryDetail.getSalary().length() > 0
						&& hr.getProperty("BASIC_SALARY") != null && hr.getProperty("BASIC_SALARY").length() > 0) {
					float s = Float.valueOf(salaryDetail.getSalary()) * Float.valueOf(hr.getProperty("BASIC_SALARY"));
					salaryDetail.setBasicSalary(String.valueOf(s));
				}
				log.info("Tinh cho thang moi, hsl = " + salaryDetail.getSalary());
				salaryDetail.setMonth(month);
				salaryDetail.setYear(year);
				salaryDetail.setAdvancePayed("");
				salaryDetail.setBounus("");
				salaryDetail.setFinalSalary("");
				salaryDetail.setTaxPersonal("");
				salaryDetail.setOverTimeH("");
				salaryDetail.setOverTimeN("");
				salaryDetail.setOverTimeW("");
				salaryDetail.setSubsidize("");
				salaryDetail.setMaketingSalary("");
				salaryDetail.setSubGas("");
				salaryDetail.setSubLunch("");
				salaryDetail.setSubPhone("");
				salaryDetail.setOverWork("");
				salaryDetail.setArrears("");
				salaryDetail.setTotalIncome("");
				salaryDetail.setTotalReduce("");
			}
			salaryDetail.setWorkedDay(null);
			WorkingDay workingDay = null;
			if (month < 10)
				workingDay = workingDayDAO.getWorkingDay(year + "-0" + month, "Cabeco");
			else
				workingDay = workingDayDAO.getWorkingDay(year + "-" + month, "Cabeco");

			float salaryPerHour = 0;
			if (workingDay.getWorkDayOfMonth() != null) {
				float workingDayOfMonth = workingDay.getWorkDayOfMonth();

				String carDriver = "";
				carDriver = hr.getProperty("WORK_SATURDAY");
				if (carDriver != null && carDriver.length() > 0) {
					// System.err.println(workingDayOfMonth + " thang " + month);
					// System.err.println(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle()
					// +"|" + carDriver);
					if (carDriver.contains(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle())) {
						log.info(salaryDetail.getFullName() + " la lai xe");
						log.info("Thang " + month + " co " + Utils.countWeekendDays(year, month) + " ngay thu 7 ");
						workingDayOfMonth = workingDayOfMonth + Utils.countWeekendDays(year, month);
						log.info("Cong them " + Utils.countWeekendDays(year, month) + " vao ngay cong chuan");
					}
				}
				// System.err.println(workingDayOfMonth);
				if (salaryDetail.getSalary() != null && salaryDetail.getSalary().length() > 0) {
					salaryPerHour = (Float.valueOf(salaryDetail.getSalary())
							* Float.valueOf(hr.getProperty("BASIC_SALARY"))) / workingDayOfMonth / 8;
					salaryPerHour = Math.round((salaryPerHour * 10) / 10);
				}
				
			} else {
				model.addAttribute("workDayDefine",
						"Vui lòng định nghĩa ngày công chuẩn cho tháng trước để việc tính lương được chính sác!");
			}
			salaryDetail.setSalaryPerHour(salaryPerHour);
			model.addAttribute("salaryPerHour", salaryPerHour);

			Map<String, String> employeeMap = this.employees();
			String name = "";
			name = employeeMap.get(String.valueOf(employeeId));
			model.addAttribute("name", name);

			model.addAttribute("salaryDetail", salaryDetail);
			model.addAttribute("employeeId", employeeId);

			model.addAttribute("formTitle", "Thêm thông tin lương chi tiết của " + name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "formSalaryDetail";
	}

	@RequestMapping(value = "/salary/insertSalaryDetail", method = RequestMethod.POST)
	public String insertSalaryDetail(Model model,
			@ModelAttribute("salaryDetailForm") @Validated SalaryDetail salaryDetail,
			final RedirectAttributes redirectAttributes) {
		try {
			// System.err.println(salaryDetail.getMonth());
			if (salaryDetail.getSalary() != null && salaryDetail.getSalary().length() > 0
					&& hr.getProperty("BASIC_SALARY") != null && hr.getProperty("BASIC_SALARY").length() > 0) {
				float s = Float.valueOf(salaryDetail.getSalary()) * Float.valueOf(hr.getProperty("BASIC_SALARY"));
				salaryDetail.setBasicSalary(String.valueOf(s));
				
			}
			WorkingDay workingDay = null;
			int month = salaryDetail.getMonth();
			int year = salaryDetail.getYear();
			int employeeId = salaryDetail.getEmployeeId();
			if (month < 10)
				workingDay = workingDayDAO.getWorkingDay(year + "-0" + month, "Cabeco");
			else
				workingDay = workingDayDAO.getWorkingDay(year + "-" + month, "Cabeco");

			float salaryPerHour = 0;
			if (workingDay.getWorkDayOfMonth() != null) {
				float workingDayOfMonth = workingDay.getWorkDayOfMonth();

				String carDriver = "";
				carDriver = hr.getProperty("WORK_SATURDAY");
				// System.err.println(workingDayOfMonth + " thang " + month);
				// System.err.println(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle()
				// +"|" + carDriver);
				if (carDriver.contains(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle())) {
					log.info(salaryDetail.getFullName() + " la lai xe");
					log.info("Thang " + month + " co " + Utils.countWeekendDays(year, month) + " ngay thu 7 ");
					workingDayOfMonth = workingDayOfMonth + Utils.countWeekendDays(year, month);
					log.info("Cong them " + Utils.countWeekendDays(year, month) + " vao ngay cong chuan");
				}

				// System.err.println(workingDayOfMonth);
				if (salaryDetail.getSalary() != null && salaryDetail.getSalary().length() > 0) {
					salaryPerHour = (Float.valueOf(salaryDetail.getSalary())
							* Float.valueOf(hr.getProperty("BASIC_SALARY"))) / workingDayOfMonth / 8;
					salaryPerHour = Math.round((salaryPerHour * 10) / 10);
				}

				// Không lv đủ cả tháng
				String workedDay = salaryDetail.getWorkedDay();
				if (workedDay != null && workedDay.length() > 0) {
					float currentSalary = (Float.parseFloat(workedDay) / workingDayOfMonth)
							* (Float.valueOf(Float.parseFloat(salaryDetail.getSalary()))
									* Float.valueOf(hr.getProperty("BASIC_SALARY")));
					log.info("Ngay lv thuc te trong thang: " + workedDay + "/" + workingDayOfMonth);
					salaryDetail.setSalaryForWorkedDay(String.valueOf(currentSalary));
				}
			} else {
				model.addAttribute("workDayDefine",
						"Vui lòng định nghĩa ngày công chuẩn cho tháng trước để việc tính lương được chính sác!");
			}
			salaryDetail.setSalaryPerHour(salaryPerHour);
			if(salaryDetail.getMaketingSalary() != null && salaryDetail.getMaketingSalary().length() > 0) {
				model.addAttribute("maintainSalary", salaryPerHour*Float.valueOf(salaryDetail.getMaketingSalary()));
			}
			// ting toan luong over time
			double overTimeSalary = 0;
			// float salaryPerHour = salaryDetail.getSalaryPerHour();
			String overTimeN = salaryDetail.getOverTimeN();
			if (overTimeN != null && overTimeN.length() > 0 && Float.parseFloat(overTimeN) > 0) {
				overTimeSalary = overTimeSalary + Float.parseFloat(overTimeN) * salaryPerHour * 1.5;
				// System.err.println(overTimeSalary + " overTime N controller " + overTimeN);
			}
			String overTimeW = salaryDetail.getOverTimeW();
			if (overTimeW != null && overTimeW.length() > 0 && Float.parseFloat(overTimeW) > 0) {
				overTimeSalary = overTimeSalary + Float.parseFloat(overTimeW) * salaryPerHour * 2;
				// System.err.println(overTimeSalary + " overTime W controller " + overTimeW);
			}
			String overTimeH = salaryDetail.getOverTimeH();
			if (overTimeH != null && overTimeH.length() > 0 && Float.parseFloat(overTimeH) > 0) {
				overTimeSalary = overTimeSalary + Float.parseFloat(overTimeH) * salaryPerHour * 3;
				// System.err.println(overTimeSalary + " overTime H controller " + overTimeH);
			}

			// System.err.println("salaryPerHour: " + salaryPerHour + " overTimeSalary: " +
			// overTimeSalary);
			overTimeSalary = Math.round(overTimeSalary);
			salaryDetail.setOverTimeSalary(String.valueOf(overTimeSalary));

			if (salaryDetail.getSalaryInsurance() != null && salaryDetail.getSalaryInsurance().length() > 0)
				salaryDetail.setPayedInsurance(
						String.valueOf(Float.parseFloat(salaryDetail.getSalaryInsurance()) * 10.5 / 100));

			salaryDAO.insertSalaryDetail(salaryDetail);
			model.addAttribute("salaryDetail", salaryDetail);
			model.addAttribute("employeeId", salaryDetail.getEmployeeId());
			model.addAttribute("salaryPerHour", salaryPerHour);
			
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("message", "Thêm thông tin lương chi tiết của thành công!");

		} catch (Exception e) {
			log.error(e, e);
		}
		return "updateSalaryDetail"; // editSalaryDetailForm(model, salaryDetail);
	}

	@RequestMapping("/salary/editSalaryDetailForm")
	public String editSalaryDetailForm(Model model,
			@ModelAttribute("salaryDetail") @Validated SalaryDetail salaryDetail) {
		try {
			// SalaryDetail salaryDetail = new SalaryDetail();

			WorkingDay workingDay = null;
			int month = salaryDetail.getMonth();
			int year = salaryDetail.getYear();
			int employeeId = salaryDetail.getEmployeeId();
			salaryDetail = salaryDAO.getSalaryDetail(employeeId, month, year);
			
			if (month < 10)
				workingDay = workingDayDAO.getWorkingDay(year + "-0" + month, "Cabeco");
			else
				workingDay = workingDayDAO.getWorkingDay(year + "-" + month, "Cabeco");

			float salaryPerHour = 0;
			if (workingDay.getWorkDayOfMonth() != null) {
				float workingDayOfMonth = workingDay.getWorkDayOfMonth();

				String carDriver = "";
				carDriver = hr.getProperty("WORK_SATURDAY");
				// System.err.println(workingDayOfMonth + " thang " + month);
				// System.err.println(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle()
				// +"|" + carDriver);
				if (carDriver.contains(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle())) {
					log.info(salaryDetail.getFullName() + " la lai xe");
					log.info("Thang " + month + " co " + Utils.countWeekendDays(year, month) + " ngay thu 7 ");
					workingDayOfMonth = workingDayOfMonth + Utils.countWeekendDays(year, month);
					log.info("Cong them " + Utils.countWeekendDays(year, month) + " vao ngay cong chuan");
				}
				if (salaryDetail.getBasicSalary() != null && salaryDetail.getBasicSalary().length() > 0) {
					salaryPerHour = Float.valueOf(salaryDetail.getBasicSalary()) / workingDayOfMonth / 8;
					salaryPerHour = Math.round((salaryPerHour * 10) / 10);					
				}
			} else {
				model.addAttribute("workDayDefine",
						"Vui lòng định nghĩa ngày công chuẩn cho tháng trước để việc tính lương được chính sác!");
			}
			if(salaryDetail.getMaketingSalary() != null && salaryDetail.getMaketingSalary().length() > 0) {
				model.addAttribute("maintainSalary", salaryPerHour*Float.valueOf(salaryDetail.getMaketingSalary()));
			}
			salaryDetail.setSalaryPerHour(salaryPerHour);
			model.addAttribute("salaryPerHour", salaryPerHour);
			model.addAttribute("salaryDetail", salaryDetail);
			model.addAttribute("employeeId", salaryDetail.getEmployeeId());

			model.addAttribute("formTitle", "Thay đổi thông tin tính lương chi tiết của " + salaryDetail.getFullName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "updateSalaryDetail";
	}

	@RequestMapping(value = "/salary/updateSalaryDetail", method = RequestMethod.POST)
	public String updateSalaryDetail(Model model, @ModelAttribute("salaryDetail") @Validated SalaryDetail salaryDetail,
			final RedirectAttributes redirectAttributes) {
		try {
			// System.err.println(salaryDetail.getSalary());
			WorkingDay workingDay = null;
			int month = salaryDetail.getMonth();
			int year = salaryDetail.getYear();
			int employeeId = salaryDetail.getEmployeeId();
			if (month < 10)
				workingDay = workingDayDAO.getWorkingDay(year + "-0" + month, "Cabeco");
			else
				workingDay = workingDayDAO.getWorkingDay(year + "-" + month, "Cabeco");

			float salaryPerHour = salaryDetail.getSalaryPerHour();
			// System.err.println(workingDay.getWorkDayOfMonth());
			if (workingDay.getWorkDayOfMonth() != null) {
				float workingDayOfMonth = workingDay.getWorkDayOfMonth();

				String carDriver = "";
				carDriver = hr.getProperty("WORK_SATURDAY");
				// System.err.println(workingDayOfMonth + " thang " + month);
				// System.err.println(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle()
				// +"|" + carDriver);
				if (carDriver.contains(employeeDAO.getEmployee(String.valueOf(employeeId)).getJobTitle())) {
					log.info(salaryDetail.getFullName() + " la lai xe");
					log.info("Thang " + month + " co " + Utils.countWeekendDays(year, month) + " ngay thu 7 ");
					workingDayOfMonth = workingDayOfMonth + Utils.countWeekendDays(year, month);
					log.info("Cong them " + Utils.countWeekendDays(year, month) + " vao ngay cong chuan");
				}
				
				if (salaryDetail.getSalary() != null && salaryDetail.getSalary().length() > 0) {
					salaryPerHour = (Float.valueOf(salaryDetail.getSalary())
							* Float.valueOf(hr.getProperty("BASIC_SALARY"))) / workingDayOfMonth / 8;
					salaryPerHour = Math.round(salaryPerHour);					
				}

				// Không lv đủ cả tháng
				String workedDay = salaryDetail.getWorkedDay();
				if (workedDay != null && workedDay.length() > 0) {
					log.info("Ngay lv thuc te trong thang: " + workedDay + "/" + workingDayOfMonth);
					float currentSalary = (Float.parseFloat(workedDay) / workingDayOfMonth)
							* (Float.valueOf(salaryDetail.getSalary())
									* Float.valueOf(hr.getProperty("BASIC_SALARY")));
					salaryDetail.setSalaryForWorkedDay(String.valueOf(currentSalary));
				}
			
			} else {
				model.addAttribute("workDayDefine",
						"Vui lòng định nghĩa ngày công chuẩn cho tháng trước để việc tính lương được chính sác!");
			}
			salaryDetail.setSalaryPerHour(salaryPerHour);
			if(salaryDetail.getMaketingSalary() != null && salaryDetail.getMaketingSalary().length() > 0) {
				model.addAttribute("maintainSalary", salaryPerHour*Float.valueOf(salaryDetail.getMaketingSalary()));
			}
			
			// ting toan luong over time
			double overTimeSalary = 0;
			String overTimeN = salaryDetail.getOverTimeN();
			if (overTimeN != null && overTimeN.length() > 0 && Float.parseFloat(overTimeN) > 0)
				overTimeSalary = overTimeSalary + Float.parseFloat(overTimeN) * salaryPerHour * 1.5;
			String overTimeW = salaryDetail.getOverTimeW();
			if (overTimeW != null && overTimeW.length() > 0 && Float.parseFloat(overTimeW) > 0)
				overTimeSalary = overTimeSalary + Float.parseFloat(overTimeW) * salaryPerHour * 2;
			String overTimeH = salaryDetail.getOverTimeH();
			if (overTimeH != null && overTimeH.length() > 0 && Float.parseFloat(overTimeH) > 0)
				overTimeSalary = overTimeSalary + Float.parseFloat(overTimeH) * salaryPerHour * 3;

			overTimeSalary = Math.round(overTimeSalary);
			salaryDetail.setOverTimeSalary(String.valueOf(overTimeSalary));
			if (salaryDetail.getSalaryInsurance() != null && salaryDetail.getSalaryInsurance().length() > 0)
				salaryDetail.setPayedInsurance(
						String.valueOf(Float.parseFloat(salaryDetail.getSalaryInsurance()) * 10.5 / 100));

			// update ... lay salary o bang salary info sang bang salary detail lam basic
			// salary
			if (salaryDetail.getBasicSalary() == null) {
				salaryDetail.setBasicSalary(String.valueOf(
						Float.valueOf(salaryDetail.getSalary()) * Float.valueOf(hr.getProperty("BASIC_SALARY"))));
			}

			model.addAttribute("salaryPerHour", salaryPerHour);
			model.addAttribute("employeeId", salaryDetail.getEmployeeId());
			salaryDAO.updateSalaryDetail(salaryDetail);
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("message", "Tính lại lương thành công!");

		} catch (Exception e) {
			log.error(e, e);
		}
		return "updateSalaryDetail";
	}

	@RequestMapping(value = "/salary/prepareSalary", method = RequestMethod.GET)
	public String pepareSalary(Model model, SalaryForm salaryForm) {
		try {
			// System.out.println("PepareSummarySalary 0");
			model.addAttribute("salaryReportForm", salaryForm);
			Map<String, String> departmentMap = this.departments();
			model.addAttribute("departmentMap", departmentMap);			

			model.addAttribute("formTitle", "Tùy chọn bộ phận cần tính lương");
			// System.out.println("PepareSummarySalary 1");
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "prepareSalary";
	}
	
	@RequestMapping(value = "/salary/prepareSummarySalary", method = RequestMethod.GET)
	public String pepareSummarySalary(Model model, LeaveReport leaveReport) {
		try {
			// System.out.println("PepareSummarySalary 0");
			model.addAttribute("salaryReportForm", leaveReport);

			// get list department
			Map<String, String> departmentMap = this.departments();
			model.addAttribute("departmentMap", departmentMap);	

			// get list employee id
			Map<String, String> employeeMap = this.employees();
			model.addAttribute("employeeMap", employeeMap);

			model.addAttribute("formTitle", "Tùy chọn phạm vi cần thống kê lương");
			// System.out.println("PepareSummarySalary 1");
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "prepareSummarySalary";
	}

	@RequestMapping(value = "/salary/generateSalaryReport", method = RequestMethod.POST)
	public String generateSalaryReport(Model model, HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("salaryReportForm") @Validated LeaveReport leaveReport,
			@ModelAttribute("salaryForm") SalaryForm form, final RedirectAttributes redirectAttributes)
			throws Exception {
		try {
			Map<String, String> employeeMap = this.employees();
			String name = "";
			if (leaveReport.getMonthReport() == null || Integer.parseInt(leaveReport.getMonthReport()) == 0) {
				if (leaveReport.getEmployeeId() > 0) {
					String id = String.valueOf(leaveReport.getEmployeeId());
					name = employeeMap.get(id);
					model.addAttribute("formTitle",
							"Thông tin thông kê lương của " + name + " năm " + leaveReport.getYearReport());
					SalaryReport salaryReport = new SalaryReport();
					salaryReport = salaryDAO.getSalaryReport(leaveReport.getEmployeeId(), leaveReport.getMonthReport(),
							leaveReport.getYearReport());
					model.addAttribute("salaryReport", salaryReport);
					return "summarySalaryReport";
				} else {
					List<SalaryReportPerEmployee> list = salaryDAO.getSalaryReportDetail(leaveReport.getYearReport());
					model.addAttribute("formTitle",
							"Thông tin thông kê lương nhân viên năm " + leaveReport.getYearReport());
					// System.err.println("fan trang");
					// System.err.println("fan trang" + form.getYearReport());
					// System.err.println("fan trang" + leaveReport.getYearReport());
					if (form.getNumberRecordsOfPage() == 0) {
						form.setNumberRecordsOfPage(25);
					}
					if (form.getPageIndex() == 0) {
						form.setPageIndex(1);
					}

					form.setTotalRecords(list.size());

					int totalPages = form.getTotalRecords() % form.getNumberRecordsOfPage() > 0
							? form.getTotalRecords() / form.getNumberRecordsOfPage() + 1
							: form.getTotalRecords() / form.getNumberRecordsOfPage();
					form.setTotalPages(totalPages);

					List<SalaryReportPerEmployee> listSalaryForPage = new ArrayList<SalaryReportPerEmployee>();

					if (form.getPageIndex() < totalPages) {
						if (form.getPageIndex() == 1) {
							for (int i = 0; i < form.getNumberRecordsOfPage(); i++) {
								SalaryReportPerEmployee salary = new SalaryReportPerEmployee();
								salary = list.get(i);
								listSalaryForPage.add(salary);
							}
						} else if (form.getPageIndex() > 1) {
							for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
									.getPageIndex() * form.getNumberRecordsOfPage(); i++) {
								SalaryReportPerEmployee salary = new SalaryReportPerEmployee();
								salary = list.get(i);
								listSalaryForPage.add(salary);
							}
						}
					} else if (form.getPageIndex() == totalPages) {
						for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
								.getTotalRecords(); i++) {
							SalaryReportPerEmployee salary = new SalaryReportPerEmployee();
							salary = list.get(i);
							listSalaryForPage.add(salary);
						}
					}
					// form.setYearReport(Integer.parseInt(leaveReport.getYearReport()));
					model.addAttribute("salaryForm", form);
					model.addAttribute("salaryDetails", listSalaryForPage);
					model.addAttribute("formTitle",
							"Thông tin thông kê lương nhân viên tổng cả năm " + leaveReport.getYearReport());
					// model.addAttribute("listSalaryReportDetail", listSalaryReportDetail);
					SalaryReport salaryReport = salaryDAO.getSalaryReport(leaveReport.getEmployeeId(),
							leaveReport.getMonthReport(), leaveReport.getYearReport());
					model.addAttribute("salaryReport", salaryReport);
					// model.addAttribute("yearReport", leaveReport.getYearReport());
					return "listSalarySumaryDetail";
				}
			} else {
				if (leaveReport.getEmployeeId() > 0) {
					String id = String.valueOf(leaveReport.getEmployeeId());
					name = employeeMap.get(id);
					model.addAttribute("formTitle", "Thông tin thông kê lương của " + name + " tháng "
							+ leaveReport.getMonthReport() + ", năm " + leaveReport.getYearReport());
					SalaryReport salaryReport = new SalaryReport();
					salaryReport = salaryDAO.getSalaryReport(leaveReport.getEmployeeId(), leaveReport.getMonthReport(),
							leaveReport.getYearReport());
					model.addAttribute("salaryReport", salaryReport);

					return "summarySalaryReport";
				} else {
					List<SalaryDetail> list = salaryDAO.getSalaryReportDetail(leaveReport.getMonthReport(),
							leaveReport.getYearReport());

					if (form.getNumberRecordsOfPage() == 0) {
						form.setNumberRecordsOfPage(25);
					}
					if (form.getPageIndex() == 0) {
						form.setPageIndex(1);
					}

					form.setTotalRecords(list.size());

					int totalPages = form.getTotalRecords() % form.getNumberRecordsOfPage() > 0
							? form.getTotalRecords() / form.getNumberRecordsOfPage() + 1
							: form.getTotalRecords() / form.getNumberRecordsOfPage();
					form.setTotalPages(totalPages);

					List<SalaryDetail> listSalaryForPage = new ArrayList<SalaryDetail>();

					if (form.getPageIndex() < totalPages) {
						if (form.getPageIndex() == 1) {
							for (int i = 0; i < form.getNumberRecordsOfPage(); i++) {
								SalaryDetail salary = new SalaryDetail();
								salary = list.get(i);
								listSalaryForPage.add(salary);
							}
						} else if (form.getPageIndex() > 1) {
							for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
									.getPageIndex() * form.getNumberRecordsOfPage(); i++) {
								SalaryDetail salary = new SalaryDetail();
								salary = list.get(i);
								listSalaryForPage.add(salary);
							}
						}
					} else if (form.getPageIndex() == totalPages) {
						for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
								.getTotalRecords(); i++) {
							SalaryDetail salary = new SalaryDetail();
							salary = list.get(i);
							listSalaryForPage.add(salary);
						}
					}
					SalaryReport salaryReport = salaryDAO.getSalaryReport(leaveReport.getEmployeeId(),
							leaveReport.getMonthReport(), leaveReport.getYearReport());
					model.addAttribute("salaryReport", salaryReport);
					model.addAttribute("salaryForm", form);
					model.addAttribute("salaryDetails", listSalaryForPage);
					model.addAttribute("formTitle", "Thông tin thông kê lương nhân viên tháng "
							+ leaveReport.getMonthReport() + ", năm " + leaveReport.getYearReport());
					// model.addAttribute("listSalaryReportDetail", listSalaryReportDetail);
					return "listSalarySumaryDetail";
				}
			}
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "summarySalaryReport";
	}
	
	
	//-------------------------------------------------------------------------
	
	@RequestMapping(value = "/salary/prepareProductSold", method = RequestMethod.GET)
	public String pepareProductSold(Model model, LeaveReport leaveReport) {
		try {
			// System.out.println("PepareSummarySalary 0");
			model.addAttribute("salaryReportForm", leaveReport);
			Map<String, String> departmentMap = this.departments();
			model.addAttribute("departmentMap", departmentMap);			

			model.addAttribute("formTitle", "Tùy chọn tháng cần tính sản lượng, cho tính lương sản phẩm");
			// System.out.println("PepareSummarySalary 1");
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "prepareProductSold";
	}
	
	@RequestMapping(value = { "/salary/listProductSold" })
	public String ListProductSold(Model model, @ModelAttribute("salaryReportForm") @Validated LeaveReport leaveReport, @RequestParam(required = false, name="month") String month,
			@RequestParam(required = false, name="department") String department, @RequestParam(required = false, name="message") String message) {
		try {
			if(month == null || month.isEmpty())
				month = leaveReport.getYearReport() + "-" + leaveReport.getMonthReport();
			List<ProductSold> list = productSoldDAO.getProductSoldByMonth(department, month);	
			String totalMoneyIncome = productSoldDAO.getMoneyIncome(month, department);
			if(list.size() < 1)
				model.addAttribute("message1", "Chưa có thông tin sản lượng sản phẩm trong tháng " + month + ", bộ phận " + department);
			model.addAttribute("month", month);
			model.addAttribute("message", message);
			model.addAttribute("totalMoneyIncome", totalMoneyIncome);
			model.addAttribute("productSold", list);
			model.addAttribute("formTitle", "Danh sách sản phẩm đã bán trong tháng " + month + ", bộ phận " + department);
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "listProductSold";
	}
	
	@RequestMapping(value = "/salary/addProductSold", method = RequestMethod.POST)
	public String addProductSold(Model model, @ModelAttribute("productSoldForm") @Validated ProductSold productSold,
			final RedirectAttributes redirectAttributes) {
		LeaveReport leaveReport = new LeaveReport();
		String message = null;
		try {			
			int rowInsert = productSoldDAO.insertProductSold(productSold);
			// Add message to flash scope
			System.err.println("rowInsert = " + rowInsert);
			
			if(rowInsert > 0)
				message = "Thêm thông tin sản phẩm đã bán thành công!";
			else
				message = "Không thêm được thông tin cho sản phẩm mã " + productSold.getCode() + ", cho tháng " + productSold.getMonth() + " . Vui lòng kiểm tra lại, có thể sản phẩm đó đã tồn tại ...";
			leaveReport.setMonthReport(productSold.getMonth().substring(4, 5));
			leaveReport.setYearReport(productSold.getMonth().substring(0, 3));
		} catch (Exception e) {
			log.error(e, e);
		}		
		
		return ListProductSold(model, leaveReport, productSold.getMonth(), productSold.getDepartment(), message);
	}

	@RequestMapping(value = "/salary/updateProductSold", method = RequestMethod.POST)
	public String updateProductSold(Model model, @ModelAttribute("productSoldForm") @Validated ProductSold productSold,
			final RedirectAttributes redirectAttributes) {
		LeaveReport leaveReport = new LeaveReport();
		String message = null;
		try {
			int rowUpdate = productSoldDAO.updateProductSold(productSold);
			// Add message to flash scope			
			if(rowUpdate > 0)
				message = "Cập nhật thông tin sản phẩm đã bán thành công!";
			else
				message = "Không thể cập nhật thông tin sản phẩm " + productSold.getCode() + ", cho tháng " + productSold.getMonth() + ". Vui lòng kiểm tra lại";
			leaveReport.setMonthReport(productSold.getMonth().substring(4, 5));
			leaveReport.setYearReport(productSold.getMonth().substring(0, 3));
		} catch (Exception e) {
			log.error(e, e);
		}
		return ListProductSold(model, leaveReport, productSold.getMonth(), productSold.getDepartment(), message);
	}

	private String productSoldForm(Model model, ProductSold productSold) {
		
		// get list product id
		Map<String, String> productMap = this.products();
		model.addAttribute("productMap", productMap);

		String actionform = "";
		if (productSold.getCode() != null) {
			model.addAttribute("formTitle", "Sửa thông tin sản phẩm đã bán ");
			actionform = "editProductSold";
		} else {
			model.addAttribute("formTitle", "Thêm mới thông tin sản phẩm đã bán tháng " + productSold.getMonth());
			productSold.setScale("100");
			actionform = "insertProductSold";
		}
		model.addAttribute("productSoldForm", productSold);
		return actionform;
	}

	@RequestMapping("/salary/insertProductSold")
	public String insertProductSold(Model model, @RequestParam("month") String month,  @RequestParam("department") String department) {
		ProductSold productSold = new ProductSold();
		productSold.setMonth(month);
		productSold.setDepartment(department);
		return this.productSoldForm(model, productSold);
	}

	@RequestMapping("/salary/editProductSold")
	public String editProductSold(Model model, @RequestParam("month") String month,  @RequestParam("department") String department, @RequestParam("productCode") String productCode) {
		ProductSold productSold = null;
		if (productCode != null && productCode.length() > 0 && month != null && month.length() > 0) {
			productSold = productSoldDAO.getProductSold(department, month, productCode);
		}
		if (productSold == null) {
			return "redirect:/salary/listProductSold/";
		}

		return this.productSoldForm(model, productSold);
	}
	
	private Map<String, String> products() {
		Map<String, String> productMap = new LinkedHashMap<String, String>();
		try {
			List<Product> list = productSoldDAO.getProducts();
			Product product = new Product();
			for (int i = 0; i < list.size(); i++) {
				product = (Product) list.get(i);
				productMap.put(product.getCode(), product.getName());
			}

		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return productMap;
	}

	private Map<String, String> departments() {
		Map<String, String> departmentMap = new LinkedHashMap<String, String>();
		try {
			List<Department> list = departmentDAO.getDepartments();
			Department department = new Department();
			for (int i = 0; i < list.size(); i++) {
				department = (Department) list.get(i);				
				departmentMap.put(department.getDepartmentId(), department.getDepartmentName());
			}

		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return departmentMap;
	}

	// For Ajax
	
	@RequestMapping("/salary/selection")
	public @ResponseBody List<EmployeeInfo> employeesByDepartment(@RequestParam("department") String department) {
		List<EmployeeInfo> list = null;
		if (!department.equalsIgnoreCase("all"))
			list = employeeDAO.getEmployeesByDepartment(department);
		else
			list = employeeDAO.getEmployees();

		return list;
	}
	 
}
