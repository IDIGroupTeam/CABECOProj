package com.idi.hr.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.idi.hr.bean.Product;
import com.idi.hr.bean.ProductSold;
import com.idi.hr.common.PropertiesManager;
import com.idi.hr.mapper.ProductMapper;
import com.idi.hr.mapper.ProductSoldMapper;

public class ProductSoldDAO extends JdbcDaoSupport {

	private static final Logger log = Logger.getLogger(ProductSoldDAO.class.getName());
	private JdbcTemplate jdbcTmpl;

	public JdbcTemplate getJdbcTmpl() {
		return jdbcTmpl;
	}

	public void setJdbcTmpl(JdbcTemplate jdbcTmpl) {
		this.jdbcTmpl = jdbcTmpl;
	}

	@Autowired
	public ProductSoldDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	PropertiesManager hr = new PropertiesManager("cabecohr.properties");

	/**
	 * Get workingDays from DB
	 * 
	 * @return List of ProductSold
	 * @throws Exception
	 */
	public List<ProductSold> getProductSoldByMonth(String department, String month) {

		String sql = hr.getProperty("GET_PRODUCT_SOLD_BY_MONTH").toString();
		log.info("GET_PRODUCT_SOLD_BY_MONTH query: " + sql);
		ProductSoldMapper mapper = new ProductSoldMapper();
		Object[] params = new Object[] { department, month };

		List<ProductSold> list = jdbcTmpl.query(sql, params, mapper);
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public List<Product> getProducts() {

		String sql = hr.getProperty("GET_PRODUCTS").toString();
		log.info("GET_PRODUCTS query: " + sql);
		ProductMapper mapper = new ProductMapper();

		List<Product> list = jdbcTmpl.query(sql, mapper);
		return list;
	}
	
	/**
	 * get ProductSoldByMonth by month, product code
	 * 
	 * @param month, forCompany
	 * @return workingDay object
	 */
	public ProductSold getProductSold(String department, String month, String productCode) {
		ProductSold productSold = new ProductSold();
		try {
			String sql = hr.get("GET_PRODUCT_SOLD").toString();
			log.info("GET_PRODUCT_SOLD query: " + sql);
			Object[] params = new Object[] { department, month, productCode };
			System.out.println(month + "|" + productCode ); 
			ProductSoldMapper mapper = new ProductSoldMapper();
	
			productSold = jdbcTmpl.queryForObject(sql, params, mapper);
		}catch(Exception e) {
			//workingDay.setWorkDayOfMonth(0);
			//productSold.setComment("Tháng này chưa định nghĩa ngày công chuẩn");
		}
		return productSold;
	}

	/**
	 * Insert a ProductSold into database
	 * 
	 * @param ProductSold
	 */
	public int insertProductSold(ProductSold productSold) throws Exception {
		int rowInsert = 0;
		try {
			
			log.info("Insert new productSold ....");
			String sql = hr.getProperty("INSERT_PRODUCT_SOLD").toString();
			log.info("INSERT_PRODUCT_SOLD query: " + sql);
			String moneyIncome;
			if(productSold.getPrice() != null && productSold.getAmount() != null && productSold.getScale() != null) {
				moneyIncome = String.valueOf(Integer.valueOf(productSold.getPrice())*Integer.valueOf(productSold.getAmount())*Integer.valueOf(productSold.getScale()));
				productSold.setMoneyIncome(moneyIncome);
			}
			Object[] params = new Object[] { productSold.getCode(), productSold.getPrice(), productSold.getAmount(),
					productSold.getMonth(),	productSold.getScale(), productSold.getMoneyIncome(), productSold.getDepartment(), productSold.getComment()};
			rowInsert = jdbcTmpl.update(sql, params);

		} catch (Exception e) {
			log.error(e, e);
			return 0;
		}
		return rowInsert;
	}

	/**
	 * Update a ProductSold into database
	 * 
	 * @param ProductSold
	 */
	public int updateProductSold(ProductSold productSold) throws Exception {
		int rowUpdate = 0;
		try {

			log.info("Update productSold " + productSold.getMonth() + ", product code " + productSold.getCode() + " ....");
			String sql = hr.getProperty("UPDATE_PRODUCT_SOLD").toString();
			log.info("UPDATE_PRODUCT_SOLD query: " + sql);
			String moneyIncome;
			if(productSold.getPrice() != null && productSold.getAmount() != null && productSold.getScale() != null) {
				moneyIncome = String.valueOf(Integer.valueOf(productSold.getPrice())*Integer.valueOf(productSold.getAmount())*Integer.valueOf(productSold.getScale()));
				System.err.println(moneyIncome);
				productSold.setMoneyIncome(moneyIncome);
			}
			Object[] params = new Object[] {productSold.getPrice(), productSold.getAmount(), productSold.getScale(),
					productSold.getMoneyIncome(), productSold.getDepartment(), productSold.getComment(), productSold.getCode(), productSold.getMonth()};
			rowUpdate = jdbcTmpl.update(sql, params);

		} catch (Exception e) {
			log.error(e, e);
			return 0;
		}
		return rowUpdate;
	}
	
	/**
	 * 
	 * @param month
	 * @return
	 */
	public String getMoneyIncome(String month, String department) {

		String sql = hr.get("GET_TOTAL_MONEY_PER_MONTH").toString();
		log.info("GET_TOTAL_MONEY_PER_MONTH query: " + sql);
		Object[] params = new Object[] { month, department };
		
		String moneyIncome = jdbcTmpl.queryForObject(sql, String.class, params);

		return moneyIncome;
	}
}
