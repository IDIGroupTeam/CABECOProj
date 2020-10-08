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
import com.idi.hr.bean.Salary4List;
import com.idi.hr.bean.SalaryDetail;
import com.idi.hr.bean.SalaryRe;
import com.idi.hr.bean.SalaryReport;
import com.idi.hr.bean.SalaryReportPerEmployee;
import com.idi.hr.bean.WorkGroup;
import com.idi.hr.bean.WorkingDay;
import com.idi.hr.common.PropertiesManager;
import com.idi.hr.common.Utils;
import com.idi.hr.dao.DepartmentDAO;
import com.idi.hr.dao.EmployeeDAO;
import com.idi.hr.dao.ProductSoldDAO;
import com.idi.hr.dao.SalaryDAO;
import com.idi.hr.dao.SalaryReDAO;
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
	
	@Autowired
	private SalaryReDAO salaryReDAO;

	PropertiesManager hr = new PropertiesManager("cabecohr.properties");
	
	@RequestMapping(value = { "/salary/salaryRe" })
	public String listSalarysRe(Model model, @ModelAttribute("salaryForm") SalaryForm form) throws Exception {
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

			List<SalaryRe> list = salaryReDAO.getListSalaryRe();

			form.setTotalRecords(list.size());

			int totalPages = form.getTotalRecords() % form.getNumberRecordsOfPage() > 0
					? form.getTotalRecords() / form.getNumberRecordsOfPage() + 1
					: form.getTotalRecords() / form.getNumberRecordsOfPage();
			form.setTotalPages(totalPages);

			List<SalaryRe> listSalaryForPage = new ArrayList<SalaryRe>();

			if (form.getPageIndex() < totalPages) {
				if (form.getPageIndex() == 1) {
					for (int i = 0; i < form.getNumberRecordsOfPage(); i++) {
						SalaryRe salary = new SalaryRe();						
						salary = list.get(i);
						salary.setGroupName(dataForWorkGroup().get(salary.getGroup()));
						listSalaryForPage.add(salary);
					}
				} else if (form.getPageIndex() > 1) {
					for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form.getPageIndex()
							* form.getNumberRecordsOfPage(); i++) {
						SalaryRe salary = new SalaryRe();						
						salary = list.get(i);
						salary.setGroupName(dataForWorkGroup().get(salary.getGroup()));
						listSalaryForPage.add(salary);
					}
				}
			} else if (form.getPageIndex() == totalPages) {
				for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
						.getTotalRecords(); i++) {
					SalaryRe salary = new SalaryRe();					
					salary = list.get(i);
					salary.setGroupName(dataForWorkGroup().get(salary.getGroup()));
					listSalaryForPage.add(salary);
				}
			}
			model.addAttribute("salaryForm", form);
			model.addAttribute("salarys", listSalaryForPage);
			model.addAttribute("formTitle", "Danh sách lương điều tiết theo công của các nhóm lao động");
		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "listSalaryReInfo";
	}
	
	@RequestMapping(value = "/salary/insertSalaryRe", method = RequestMethod.POST)
	public String insertSalaryRe(Model model, @ModelAttribute("salaryForm") @Validated SalaryRe salaryRe, final RedirectAttributes redirectAttributes) throws Exception{
		
		try {		
			int rowInsert = salaryReDAO.insertSalaryRe(salaryRe);
			// Add message to flash scope
			if(rowInsert > 0)
				model.addAttribute("message", "Thêm thông tin điều tiết thành công!");
		} catch (Exception e) {
			log.error(e, e);
		}
		SalaryForm form = new SalaryForm();
		return listSalarysRe(model, form);	
	}

	@RequestMapping(value = "/salary/updateSalaryRe", method = RequestMethod.POST)
	public String updateSalaryRe(Model model, @ModelAttribute("salaryForm") @Validated SalaryRe salaryRe, final RedirectAttributes redirectAttributes) throws Exception{		
		try {					
			int rowUpdated = salaryReDAO.updateSalaryRe(salaryRe);
			// Add message to flash scope
			if(rowUpdated > 0)
				model.addAttribute("message", "Cập nhật thông điều tiết thành công!");			
		} catch (Exception e) {
			log.error(e, e);
		}
		SalaryForm form = new SalaryForm();
		return listSalarysRe(model, form);
	}

	@RequestMapping(value = "/salary/insertSalaryReForm")
	public String insertSalaryReForm(Model model, SalaryRe salaryRe) throws Exception{
		
		Calendar now = Calendar.getInstance();
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);				
		salaryRe.setMonth(month);
		salaryRe.setYear(year);		
		Map<String, String> workGroupMap = this.dataForWorkGroup();
		model.addAttribute("workGroupMap", workGroupMap);	
		model.addAttribute("salaryForm", salaryRe);
		model.addAttribute("formTitle", "Thêm mới thông tin điều tiết");

		return "insertSalaryReInfo";
	}
	
	@RequestMapping(value = "/salary/updateSalaryReForm")
	public String updateSalaryReForm(Model model, @RequestParam(required = false, name="group") String group,
			@RequestParam(required = false, name="month", defaultValue = "0") int month, 
			@RequestParam(required = false, name="year", defaultValue = "0") int year) throws Exception{
		Map<String, String> workGroupMap = this.dataForWorkGroup();
		model.addAttribute("workGroupMap", workGroupMap);		
		SalaryRe salaryRe = new SalaryRe();
		salaryRe = salaryReDAO.getSalaryRe(group, month, year);
		model.addAttribute("salaryForm", salaryRe);
		model.addAttribute("formTitle", "Sửa thông tin điều tiết");

		return "editSalaryReInfo";
	}
	
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
	
	@RequestMapping(value = { "/salary/listSalarysByGroup" })
	public String listSalarysByGroup(Model model, @ModelAttribute("salaryForm") SalaryForm form,
			@RequestParam(required = false, name="month", defaultValue = "0") int month, @RequestParam(required = false, name="year", defaultValue = "0") int year) throws Exception {
		try {
			if(year > 0)
				form.setYearReport(year);
			if(month > 0)
				form.setMonthReport(month);				
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
			
			List<Salary4List> list = null;
			if (form.getMonthReport() < 10)
				list = salaryDAO.getSalarysByGoupAndMonth(form.getGroup(), "0" + String.valueOf(form.getMonthReport()), String.valueOf(form.getYearReport()));
			else
				list = salaryDAO.getSalarysByGoupAndMonth(form.getGroup(), String.valueOf(form.getMonthReport()), String.valueOf(form.getYearReport()));			
			
			form.setTotalRecords(list.size());

			int totalPages = form.getTotalRecords() % form.getNumberRecordsOfPage() > 0
					? form.getTotalRecords() / form.getNumberRecordsOfPage() + 1
					: form.getTotalRecords() / form.getNumberRecordsOfPage();
			form.setTotalPages(totalPages);

			List<Salary4List> listSalaryForPage = new ArrayList<Salary4List>();

			if (form.getPageIndex() < totalPages) {
				if (form.getPageIndex() == 1) {
					for (int i = 0; i < form.getNumberRecordsOfPage(); i++) {
						Salary4List salary = new Salary4List();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				} else if (form.getPageIndex() > 1) {
					for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form.getPageIndex()
							* form.getNumberRecordsOfPage(); i++) {
						Salary4List salary = new Salary4List();
						salary = list.get(i);
						listSalaryForPage.add(salary);
					}
				}
			} else if (form.getPageIndex() == totalPages) {
				for (int i = ((form.getPageIndex() - 1) * form.getNumberRecordsOfPage()); i < form
						.getTotalRecords(); i++) {
					Salary4List salary = new Salary4List();
					salary = list.get(i);
					listSalaryForPage.add(salary);
				}
			}
			if(list.size() > 0) {
				//new
				//List<Integer> eIds = employeeDAO.getEmployeesIdByGroup(form.getGroup());
				/*
				 * boolean onefilled = false; if(eIds.size() > 0) { for(int i=0; i <
				 * eIds.size(); i++) { System.err.println(eIds.get(i) +"|"+
				 * form.getMonthReport()+"|"+ form.getYearReport()); try { SalaryDetail
				 * salaryDetail = salaryDAO.getSalaryDetail(eIds.get(i), form.getMonthReport(),
				 * form.getYearReport()); if(salaryDetail.getEmployeeId() > 0) onefilled = true;
				 * }catch(Exception e) { //onefilled = false; } } }
				 * 
				 * if(onefilled) model.addAttribute("filled", "YES"); else
				 * model.addAttribute("filled", null);
				 */
				//old
				if(list.size() != salaryDAO.countMembers(form.getMonthReport(), form.getYearReport(), form.getGroup())) {
					model.addAttribute("filled", null);
					System.out.println("Chua dien het ngay cong: " + list.size());
				}else {
					model.addAttribute("filled", "YES");
					System.out.println("Da dien het ngay cong: " + list.size() + "|" + salaryDAO.countMembers(form.getMonthReport(), form.getYearReport(), form.getGroup()));
				}
			}else {
				model.addAttribute("message", "Hiện tại nhóm lao động này không có nhân viên nào!");
			}
			model.addAttribute("salaryForm", form);
			model.addAttribute("salarys", listSalaryForPage);
			model.addAttribute("formTitle", "Danh sách lương của nhân viên thuộc nhóm lao động " + dataForWorkGroup().get(form.getGroup()) + " tháng " + form.getMonthReport() + ", " + form.getYearReport());
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
	
	private Map<String, String> employeesNoInfoByGroup(String group) {
		Map<String, String> employeeMap = new LinkedHashMap<String, String>();
		try {
			List<EmployeeInfo> list = employeeDAO.getEmployeesForInsertSalaryByGroup(group);
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
	public String addSalary(Model model, @ModelAttribute("salaryForm") @Validated Salary salary, @RequestParam(required = false, name="month") int month, 
			@RequestParam(required = false, name="year") int year,	final RedirectAttributes redirectAttributes) throws Exception{
		SalaryForm form = new SalaryForm();
		try {
			form.setGroup(salary.getGroup());
			salaryDAO.insertSalary(salary);
			// Add message to flash scope
			model.addAttribute("message", "Thêm thông tin lương nhân viên thành công!");

		} catch (Exception e) {
			log.error(e, e);
		}
		return listSalarysByGroup(model, form, month, year);
	}

	@RequestMapping(value = "/salary/updateSalary", method = RequestMethod.POST)
	public String updateSalary(Model model, @ModelAttribute("salaryForm") @Validated Salary salary, @RequestParam(required = false, name="month") int month, 
			@RequestParam(required = false, name="year") int year, final RedirectAttributes redirectAttributes) throws Exception{
		SalaryForm form = new SalaryForm();
		try {
			form.setGroup(salary.getGroup());			
			salaryDAO.updateSalary(salary);
			// Add message to flash scope
			model.addAttribute("message", "Cập nhật thông tin lương nhân viên thành công!");

		} catch (Exception e) {
			log.error(e, e);
		}
		return listSalarysByGroup(model, form, month, year);
	}

	private String salaryForm(Model model, Salary salary) throws Exception{
		model.addAttribute("salaryForm", salary);
		// get list employee id
		Map<String, String> employeeMap = this.employeesNoInfoByGroup(salary.getGroup());
		model.addAttribute("employeeMap", employeeMap);
		if(employeeMap.size() == 0)
			model.addAttribute("addedAll", " >>> Đã điền đầy đủ thông tin lương cơ bản cho toàn bộ nhân viên thuộc nhóm lao động này <<< ");

		String actionform = "";
		if (salary.getEmployeeId() > 0) {
			model.addAttribute("formTitle", "Sửa thông tin lương nhân viên thuộc nhóm lao động " + dataForWorkGroup().get(salary.getGroup()));
			actionform = "editSalaryInfo";
		} else {
			model.addAttribute("formTitle", "Thêm mới thông tin lương nhân viên vào nhóm lao động " + dataForWorkGroup().get(salary.getGroup()));
			actionform = "insertSalaryInfo";
		}
		return actionform;
	}

	@RequestMapping("/salary/insertSalary")
	public String addSalary(Model model, @RequestParam(required = false, name="group") String group,
			@RequestParam(required = false, name="month") int month, @RequestParam(required = false, name="year") int year) throws Exception {
		Salary salary = new Salary();
		salary.setConstSalary("100");
		salary.setGroup(group);
		salary.setMonth(month);
		salary.setYear(year);
		return this.salaryForm(model, salary);
	}

	@RequestMapping("/salary/editSalary")
	public String editSalary(Model model, @RequestParam("employeeId") int employeeId, @RequestParam(required = false, name="group") String group,
			@RequestParam(required = false, name="month") int month, @RequestParam(required = false, name="year") int year) throws Exception{
		Salary salary = null;
		if (employeeId > 0) {
			salary = salaryDAO.getSalary(employeeId);
			salary.setGroup(group);
			salary.setMonth(month);
			salary.setYear(year);
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
			System.err.println("group from salary detail: " + salaryDetail.getWorkGroup());
			System.err.println("group from epmloyee: " + salaryDetail.getGroup());
			String workGroup =  salaryDetail.getGroup();
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
				salaryDetail = salaryDAO.getSalaryDetail(employeeId, month, year, workGroup);
				salaryDetail.setWorkGroup(workGroup);
			} catch (Exception e) {
				salaryDetail = salaryDAO.getSalaryDetail(employeeId, 0, 0, workGroup);
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
				//salaryDetail.setOverTimeH("");
				//salaryDetail.setOverTimeN("");
				//salaryDetail.setOverTimeW("");
				salaryDetail.setSubsidize("");
				salaryDetail.setMaintainDay("");
				salaryDetail.setSubGas("");
				salaryDetail.setSubLunch("");
				salaryDetail.setSubPhone("");
				salaryDetail.setSubSkill("");
				salaryDetail.setOverWork("");
				salaryDetail.setArrears("");
				salaryDetail.setTotalIncome("");
				salaryDetail.setTotalReduce("");
				salaryDetail.setWorkedDay(null);
				//System.err.println("group from epmloyee: " + salaryDetail.getGroup());
				salaryDetail.setWorkGroup(workGroup);
			}			
			
			/*
			 WorkingDay workingDay = null; if (month < 10) workingDay =
			 workingDayDAO.getWorkingDay(year + "-0" + month, "Cabeco"); else workingDay =
			 workingDayDAO.getWorkingDay(year + "-" + month, "Cabeco");
			 

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
			 */
			
			salaryDetail.setWorkedTime("");
			salaryDetail.setWorkedTimePrice("");
			salaryDetail.setSubInsurance("");
			
			Map<String, String> employeeMap = this.employees();
			String name = "";
			name = employeeMap.get(String.valueOf(employeeId));
			model.addAttribute("name", name);
			
			model.addAttribute("salaryDetail", salaryDetail);
			model.addAttribute("employeeId", employeeId);
			model.addAttribute("formTitle", "Thông tin lương chi tiết của " + name + " tháng " + salaryDetail.getMonth() + ", " + salaryDetail.getYear());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "formSalaryDetail";
	}

	@RequestMapping(value = "/salary/insertSalaryDetail", method = RequestMethod.POST)
	public String insertSalaryDetail(Model model,
			@ModelAttribute("salaryDetailForm") @Validated SalaryDetail salaryDetail,
			final RedirectAttributes redirectAttributes) throws Exception {
		try {
			// System.err.println(salaryDetail.getMonth());
			if (salaryDetail.getSalary() != null && salaryDetail.getSalary().length() > 0
					&& hr.getProperty("BASIC_SALARY") != null && hr.getProperty("BASIC_SALARY").length() > 0) {
				float s = Float.valueOf(salaryDetail.getSalary()) * Float.valueOf(hr.getProperty("BASIC_SALARY"));
				salaryDetail.setBasicSalary(String.valueOf(s));
				System.out.println("BasicSalary: " + s);
			}
			/*
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
			salaryDetail.setOverTimeSalary(String.valueOf(overTimeSalary)); */
			
			System.out.println("subInsurance in controller: " + salaryDetail.getSubInsurance());
			System.out.println("bonus: " + salaryDetail.getBounus());
			System.out.println("getWorkGroup: " + salaryDetail.getWorkGroup());
			System.out.println("salaryDetail.getSaProduct() in controller: " + salaryDetail.getSaProduct());
			if (salaryDetail.getSalaryInsurance() != null && salaryDetail.getSalaryInsurance().trim().length() > 0) {
				if(salaryDetail.getSubInsurance() != null && salaryDetail.getSubInsurance().trim().length() > 0) {
					salaryDetail.setPayedInsurance(String.valueOf((Float.parseFloat(salaryDetail.getSalaryInsurance()) + Float.parseFloat(salaryDetail.getSubInsurance().replaceAll(",",""))) * 10.5 / 100));
					salaryDetail.setUnionFee(String.valueOf((Float.parseFloat(salaryDetail.getSalaryInsurance()) + Float.parseFloat(salaryDetail.getSubInsurance().replaceAll(",",""))) * 1/100));
				}else {
					salaryDetail.setPayedInsurance(String.valueOf(Float.parseFloat(salaryDetail.getSalaryInsurance()) * 10.5 / 100));
					salaryDetail.setUnionFee(String.valueOf(Float.parseFloat(salaryDetail.getSalaryInsurance()) * 1/100));
				}
			}
			salaryDAO.insertSalaryDetail(salaryDetail);
			model.addAttribute("salaryDetail", salaryDetail);
			model.addAttribute("employeeId", salaryDetail.getEmployeeId());
			//model.addAttribute("salaryPerHour", salaryPerHour);
			
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin lương chi tiết của thành công!");
			

		} catch (Exception e) {
			log.error(e, e);
		}
		SalaryForm form = new SalaryForm();
		form.setGroup(salaryDetail.getWorkGroup());
		 
		return listSalarysByGroup(model, form, salaryDetail.getMonth(), salaryDetail.getYear()); //"updateSalaryDetail"; // editSalaryDetailForm(model, salaryDetail); 
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
			salaryDetail = salaryDAO.getSalaryDetail(employeeId, month, year, salaryDetail.getGroup());
			
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
			if(salaryDetail.getMaintainDay() != null && salaryDetail.getMaintainDay().length() > 0) {
				model.addAttribute("maintainSalary", salaryPerHour*Float.valueOf(salaryDetail.getMaintainDay()));
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

	@RequestMapping(value = "/salary/calculatesSalary")
	public String calculatesSalary(Model model,  @RequestParam(required = false, name="group") String group,
			@RequestParam(required = false, name="month") int month, @RequestParam(required = false, name="year") int year,
			final RedirectAttributes redirectAttributes) throws Exception{
		try {
			System.err.println("calculatesSalary: " + group);
			//san luong cua phong/bo phan
			String totalMoneyIncome = "0";
			float totalMoney = 0;
			if(month < 10)
				totalMoneyIncome = productSoldDAO.getMoneyIncome(String.valueOf(year + "-0" + month), group);
			else
				totalMoneyIncome = productSoldDAO.getMoneyIncome(String.valueOf(year + "-" + month), group); 
			//System.err.println("totalMoneyIncome 1: " + totalMoneyIncome);
			try {
				totalMoney = Float.valueOf(totalMoneyIncome);
			}catch(Exception e) {
				totalMoney = 0;
			}
			
			//dieu tiet /cong
			String r = salaryReDAO.getSalaryRe(group, month, year).getValue();
			 
			//System.err.println("totalMoneyIncome 2: " + totalMoney);
			//luong dieu tiet (x ngay cong)
						
			//lay ds nv cua nhom lao dong
			List<Integer> eIds = employeeDAO.getEmployeesIdByGroup(group);
			if(eIds.size() > 0) {																														
				float sumHSL = 0;
				// tinh hs luong cho tung nv
				for(int i=0; i < eIds.size(); i++) {
					System.err.println(eIds.get(i) +"|"+ month+"|"+ year);
					SalaryDetail salaryDetail = salaryDAO.getSalaryDetail(eIds.get(i), month, year, group);
					
					//he so luong cong
					Float hsl;
					if (salaryDetail.getWorkedDay() != null && salaryDetail.getWorkedDay().trim().length() > 0)
						hsl = Float.valueOf(salaryDetail.getBasicSalary())*Float.valueOf(salaryDetail.getWorkedDay());
					else
						hsl = Float.valueOf(salaryDetail.getBasicSalary())*0;
				    
				    sumHSL = sumHSL + hsl;
				}
				//System.err.println("sumHSL " + sumHSL);
				//he so chung
				float hsc = totalMoney/sumHSL;
				//System.err.println("hs chung " + hsc);
				//tinh luong cho tung nv
				for(int j=0; j < eIds.size(); j++) {
					SalaryDetail salaryDetail = salaryDAO.getSalaryDetail(eIds.get(j), month, year, group);					
					
					// Tính lương thực nhận
					float finalSalary = 0;
					if (salaryDetail.getWorkedDay() != null && salaryDetail.getWorkedDay().trim().length() > 0)
						finalSalary = Float.valueOf(salaryDetail.getBasicSalary()) + Float.valueOf(salaryDetail.getBasicSalary())*Float.valueOf(salaryDetail.getWorkedDay())*hsc ;			
					else
						finalSalary = 0 + 0 ;
					//System.err.println("finalSalary luong sp: " + finalSalary);
					
					float luongsp = finalSalary;
					salaryDetail.setSaProduct(String.valueOf(luongsp));
					
					//tinh he so hoan thanh cong viec --> chac cabeco ko can?
					if(salaryDetail.getWorkComplete() >= 0) {
						finalSalary = finalSalary*salaryDetail.getWorkComplete()/100;				
					}
					
					//get from other table --> luong dieu tiet: update 2020-08-04 -> cho ca ngay cong theo sp + ngay cong lao dong thoi gian	
					float rSalary = 0;
					if(r != null && r.length() > 0) {
						rSalary = (Float.valueOf(salaryDetail.getMaintainDay()) + Float.valueOf(salaryDetail.getWorkedDay()))*Float.valueOf(r);
						rSalary = rSalary*100/100;
						salaryDetail.setrSalary(String.valueOf(rSalary));						
					}					
					
					//luong thoi gian (giao nhan) -> ngay cong lao dong
					float luongtgld = 0;
					System.err.println("luong thoi gian ...: " + salaryDetail.getWorkedTime() + "h, gia" + salaryDetail.getWorkedTimePrice());
					if(salaryDetail.getWorkedTime() != null && salaryDetail.getWorkedTime().trim().length() > 0
							&& salaryDetail.getWorkedTimePrice() != null && salaryDetail.getWorkedTimePrice().trim().length() > 0) {
						//System.err.println("luong thoi gian bf: " + finalSalary);
						luongtgld = Float.valueOf(salaryDetail.getWorkedTimePrice().replaceAll(",", ""))*Float.valueOf(salaryDetail.getWorkedTime().replaceAll(",", ""));
						finalSalary = finalSalary + luongtgld;
						System.err.println("luong thoi gian lao dong: " + luongtgld);
						//System.err.println("luong thoi gian at: " + finalSalary);
					}
					
					//luong cho ngay cong thoi gian (bao tri boc xep)
					//luong thoi gian - ngay cong bao tri
					float luongtg = 0;
					float luongtgnc = 0;					
					//gia luong ngay cong thoi gian: luong sp/ ngay cong ld theo san pham
					if(salaryDetail.getMaintainDay() != null && salaryDetail.getMaintainDay().length() > 0) {
						if (salaryDetail.getWorkedDay() != null && salaryDetail.getWorkedDay().trim().length() > 0)
							luongtgnc = Float.valueOf(salaryDetail.getMaintainDay())*(luongsp/Float.valueOf(salaryDetail.getWorkedDay()));
						else
							luongtgnc = 0;
						//System.err.println("luong thoi gian ngay cong: " + luongtgnc);
						finalSalary = finalSalary + luongtgnc;
					}
					//tinh tong lyong thoi gian:
					luongtg = luongtgnc + luongtgld;
					salaryDetail.setSaTime(String.valueOf(luongtg));

					// Tang		
					// float salaryPerHour = 0; if (salaryDetail.getSalaryPerHour() > 0)
					//  salaryPerHour = salaryDetail.getSalaryPerHour();					 
					
					if (salaryDetail.getBounus() != null && salaryDetail.getBounus().length() > 0) {
						finalSalary = finalSalary + Float.valueOf(salaryDetail.getBounus().replaceAll(",", ""));
						salaryDetail.setBounus(salaryDetail.getBounus().replaceAll(",", ""));
					}	
					if (salaryDetail.getSubsidize() != null && salaryDetail.getSubsidize().length() > 0) {
						finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubsidize().replaceAll(",", ""));
						salaryDetail.setSubsidize(salaryDetail.getSubsidize().replaceAll(",", ""));
					}	
					
					/*
					if (salaryDetail.getOverTimeN() != null && salaryDetail.getOverTimeN().length() > 0) {
						finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeN()) * (float) 1.5;
					}
					if (salaryDetail.getOverTimeW() != null && salaryDetail.getOverTimeW().length() > 0) {
						finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeW()) * 2;
					}
					if (salaryDetail.getOverTimeH() != null && salaryDetail.getOverTimeH().length() > 0) {
						finalSalary = finalSalary + salaryPerHour * Float.valueOf(salaryDetail.getOverTimeH()) * 3;
					}
					*/
					
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
					if (salaryDetail.getSubSkill() != null && salaryDetail.getSubSkill().length() > 0) {
						finalSalary = finalSalary + Float.valueOf(salaryDetail.getSubSkill().replaceAll(",", ""));
						salaryDetail.setSubSkill(salaryDetail.getSubSkill().replaceAll(",", ""));
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
					if (salaryDetail.getSalaryInsurance() != null && salaryDetail.getSalaryInsurance().trim().length() > 0) {						
						if(salaryDetail.getSubInsurance() != null && salaryDetail.getSubInsurance().trim().length() > 0) {
							finalSalary = finalSalary - ((Float.parseFloat(salaryDetail.getSalaryInsurance()) + Float.parseFloat(salaryDetail.getSubInsurance().replaceAll(",", ""))) * (float)10.5 / 100);
							finalSalary = finalSalary - ((Float.parseFloat(salaryDetail.getSalaryInsurance()) + Float.parseFloat(salaryDetail.getSubInsurance().replaceAll(",", ""))) * (float)1/100);
						}else {
							finalSalary = finalSalary - Float.valueOf(salaryDetail.getSalaryInsurance()) * (float) 10.5 / 100;							
						}
												
					}
					salaryDetail.setTotalReduce(String.valueOf(Float.valueOf(salaryDetail.getTotalIncome()) - finalSalary));
					
					salaryDetail.setFinalSalary(String.valueOf((double) Math.round(finalSalary)));
					
					System.err.println("finalSalary: " + eIds.get(j) + "|" + finalSalary);
					salaryDAO.updateSalaryDetail(salaryDetail);
				}					
			}			
			
			// Add message to flash scope
			redirectAttributes.addFlashAttribute("message", "Tính lương thành công!");

		} catch (Exception e) {
			log.error(e, e);
		}
		SalaryForm form = new SalaryForm();
		form.setGroup(group);
		return  listSalarysByGroup(model, form, month, year);
	}

	@RequestMapping(value = "/salary/prepareSalary", method = RequestMethod.GET)
	public String pepareSalary(Model model, SalaryForm salaryForm) {
		try {
			Calendar now = Calendar.getInstance();
			int month = now.get(Calendar.MONTH) + 1;			
			int year = now.get(Calendar.YEAR);			
			salaryForm.setMonthReport(month);
			salaryForm.setYearReport(year);
			
			//System.err.println(month + "|" + year);
			model.addAttribute("salaryReportForm", salaryForm);
			Map<String, String> workGroupMap = this.dataForWorkGroup();
			model.addAttribute("workGroupMap", workGroupMap);			

			model.addAttribute("formTitle", "Tùy chọn nhóm lao động cần tính lương");
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
			
			// nhom lao dong
			
			Map<String, String> groupMap = this.dataForWorkGroup();
			model.addAttribute("groupMap", groupMap);	

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
							leaveReport.getYearReport(), null, leaveReport.getGroup());
					model.addAttribute("salaryReport", salaryReport);
					return "summarySalaryReport";
				} else {
					List<SalaryReportPerEmployee> list = salaryDAO.getSalaryReportDetail(leaveReport.getYearReport(), leaveReport.getDepartment(), leaveReport.getGroup());
					if(leaveReport.getDepartment() !=null && leaveReport.getDepartment().equalsIgnoreCase("all"))
						model.addAttribute("formTitle",	"Thông tin thông kê lương nhân viên năm " + leaveReport.getYearReport());
					else
						model.addAttribute("formTitle",	"Thông tin thông kê lương nhân viên phòng " + departments().get(leaveReport.getDepartment()) + " năm " + leaveReport.getYearReport());

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
					//model.addAttribute("formTitle", "Thông tin thông kê lương nhân viên tổng cả năm " + leaveReport.getYearReport());
					// model.addAttribute("listSalaryReportDetail", listSalaryReportDetail);
					SalaryReport salaryReport = salaryDAO.getSalaryReport(leaveReport.getEmployeeId(),
							leaveReport.getMonthReport(), leaveReport.getYearReport(), leaveReport.getDepartment(), leaveReport.getGroup());
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
							leaveReport.getYearReport(), null, leaveReport.getGroup());
					model.addAttribute("salaryReport", salaryReport);

					return "summarySalaryReport";
				} else {
					List<SalaryDetail> list = salaryDAO.getSalaryReportDetail(leaveReport.getMonthReport(),
							leaveReport.getYearReport(), leaveReport.getDepartment(), leaveReport.getGroup());

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
							leaveReport.getMonthReport(), leaveReport.getYearReport(), leaveReport.getDepartment(), leaveReport.getGroup());
					model.addAttribute("salaryReport", salaryReport);
					model.addAttribute("salaryForm", form);
					model.addAttribute("salaryDetails", listSalaryForPage);
					if(leaveReport.getDepartment() !=null && leaveReport.getDepartment().equalsIgnoreCase("all"))
						model.addAttribute("formTitle",	"Thông tin thông kê lương nhân viên tháng "
								+ leaveReport.getMonthReport() + ", năm " + leaveReport.getYearReport());
					else
						model.addAttribute("formTitle",	"Thông tin thông kê lương nhân viên tháng "
								+ leaveReport.getMonthReport() + ", năm " + leaveReport.getYearReport() + " phòng " 
								+ departments().get(leaveReport.getDepartment()));

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
	public String pepareProductSold(Model model, SalaryForm salaryForm) {
		try {			
			Calendar now = Calendar.getInstance();
			int month = now.get(Calendar.MONTH) + 1;
			salaryForm.setMonthReport(month);
			model.addAttribute("salaryReportForm", salaryForm);
			Map<String, String> groupMap = dataForWorkGroup();
			model.addAttribute("groupMap", groupMap);		
			model.addAttribute("formTitle", "Tùy chọn tháng cần tính sản lượng, cho tính lương sản phẩm");

		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return "prepareProductSold";
	}
	
	@RequestMapping(value = { "/salary/listProductSold" })
	public String listProductSold(Model model, @ModelAttribute("salaryReportForm") @Validated LeaveReport leaveReport, @RequestParam(required = false, name="month") String month,
			@RequestParam(required = false, name="group") String group, @RequestParam(required = false, name="message") String message) {
		try {
			if(month == null || month.isEmpty())
				month = leaveReport.getYearReport() + "-" + leaveReport.getMonthReport();
			List<ProductSold> list = productSoldDAO.getProductSoldByMonth(group, month);	
			String totalMoneyIncome = productSoldDAO.getMoneyIncome(month, group);
			if(list.size() < 1)
				model.addAttribute("message1", "Chưa có thông tin sản lượng sản phẩm trong tháng " + month.substring(5, 7) + "-" + month.substring(0, 4) + ", nhóm lao động " + dataForWorkGroup().get(group));
			model.addAttribute("month", month);
			model.addAttribute("monthR", month.substring(5, 7));
			model.addAttribute("yearR", month.substring(0, 4));	
			model.addAttribute("message", message);
			model.addAttribute("totalMoneyIncome", totalMoneyIncome);
			model.addAttribute("productSold", list);
			model.addAttribute("formTitle", "Danh sách sản phẩm đã bán/làm trong tháng " + month.substring(5, 7) + "-" + month.substring(0, 4) + ", nhóm lao động " + dataForWorkGroup().get(group));
			model.addAttribute("group", group);
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
			//System.err.println("rowInsert = " + rowInsert);
			
			if(rowInsert > 0)
				message = "Thêm thông tin sản phẩm đã bán/làm thành công!";
			else
				message = "Không thêm được thông tin cho sản phẩm mã " + productSold.getCode() + ", cho tháng " + productSold.getMonth().substring(5, 7) + "-" + productSold.getMonth().substring(0, 4) + " . Vui lòng kiểm tra lại, có thể sản phẩm đó đã tồn tại ...";
			//System.err.println("productSold.getGroup(): " + productSold.getGroup());
			leaveReport.setMonthReport(productSold.getMonth().substring(5, 7));
			leaveReport.setYearReport(productSold.getMonth().substring(0, 4));
		} catch (Exception e) {
			log.error(e, e);
		}		
		
		return listProductSold(model, leaveReport, productSold.getMonth(), productSold.getGroup(), message);
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
				message = "Cập nhật thông tin sản phẩm đã bán/làm thành công!";
			else
				message = "Không thể cập nhật thông tin sản phẩm " + productSold.getCode() + ", cho tháng " + productSold.getMonth() + ". Vui lòng kiểm tra lại";
			leaveReport.setMonthReport(productSold.getMonth().substring(4, 5));
			leaveReport.setYearReport(productSold.getMonth().substring(0, 3));
		} catch (Exception e) {
			log.error(e, e);
		}
		return listProductSold(model, leaveReport, productSold.getMonth(), productSold.getGroup(), message);
	}

	private String productSoldForm(Model model, ProductSold productSold) throws Exception{
		
		// get list product id
		Map<String, String> productMap = this.products();
		model.addAttribute("productMap", productMap);

		String actionform = "";
		if (productSold.getCode() != null) {
			model.addAttribute("formTitle", "Sửa thông tin sản phẩm đã bán/làm tháng " + productSold.getMonth().substring(5, 7) + "-" + productSold.getMonth().substring(0, 4) + " của nhóm lao động " + dataForWorkGroup().get(productSold.getGroup()));
			actionform = "editProductSold";
		} else {
			model.addAttribute("formTitle", "Thêm mới thông tin sản phẩm đã bán/làm tháng " + productSold.getMonth().substring(5, 7) + "-" + productSold.getMonth().substring(0, 4) + " của nhóm lao động " + dataForWorkGroup().get(productSold.getGroup()));
			productSold.setScale("100");
			actionform = "insertProductSold";
		}
		model.addAttribute("m", productSold.getMonth().substring(5, 7));
		model.addAttribute("y", productSold.getMonth().substring(0, 4));
		model.addAttribute("productSoldForm", productSold);
		return actionform;
	}

	@RequestMapping("/salary/insertProductSold")
	public String insertProductSold(Model model, @RequestParam("month") String month,  @RequestParam("group") String group) throws Exception{
		ProductSold productSold = new ProductSold();
		productSold.setMonth(month);
		productSold.setGroup(group);
		return this.productSoldForm(model, productSold);
	}

	@RequestMapping("/salary/editProductSold")
	public String editProductSold(Model model, @RequestParam("month") String month,  @RequestParam("group") String group,
			@RequestParam("productCode") String productCode, @RequestParam("price") String price, @RequestParam("scale") String scale) throws Exception {
		ProductSold productSold = null;
		if (productCode != null && productCode.length() > 0 && month != null && month.length() > 0) {
			productSold = productSoldDAO.getProductSold(group, month, productCode, price, scale);
		}
		if (productSold == null) {
			return "redirect:/salary/listProductSold/";
		}

		return this.productSoldForm(model, productSold);
	}
	
	@RequestMapping("/salary/deleteProductSold")
	public String deleteProductSold(Model model, @RequestParam("month") String month,  @RequestParam("group") String group,
			@RequestParam("productCode") String productCode, @RequestParam("price") String price, @RequestParam("scale") String scale) throws Exception{
		
		LeaveReport leaveReport = new LeaveReport();
		productSoldDAO.deleteProductSold(group, month, productCode, price, scale);			
		
		return listProductSold(model, leaveReport, month, group, "Xóa thông tin sản phẩm thành công" );
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
	
	private Map<String, String> dataForWorkGroup() throws Exception {
		Map<String, String> workGroupMap = new LinkedHashMap<String, String>();
		try {
			List<WorkGroup> list = employeeDAO.getWorkGroups();
			WorkGroup workGroup = new WorkGroup();
			for (int i = 0; i < list.size(); i++) {
				workGroup = (WorkGroup) list.get(i);
				workGroupMap.put(workGroup.getGroupId(), workGroup.getGroupName());
			}

		} catch (Exception e) {
			log.error(e, e);
			e.printStackTrace();
		}
		return workGroupMap;
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
	 
	@RequestMapping("/salary/selectionGroup")
	public @ResponseBody List<EmployeeInfo> employeesByGroup(@RequestParam("group") String group) {
		List<EmployeeInfo> list = null;
		if (!group.equalsIgnoreCase("all"))
			list = employeeDAO.getEmployeesByGroup("%" + group + "%");
		else
			list = employeeDAO.getEmployees();

		return list;
	}
	
}
