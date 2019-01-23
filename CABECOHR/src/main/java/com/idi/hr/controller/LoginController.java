package com.idi.hr.controller;
import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

	 private static final Logger logger = Logger.getLogger(LoginController.class);
	    
		@RequestMapping(value= {"/login"},method=RequestMethod.GET)
		 public String login(@RequestParam(value="error", required= false) final String error, Model model) {
			String strMsg = "Xin điền thông tin chi tiết của bạn để truy cập";
			model.addAttribute("message", strMsg);
			
			//get username
			/**
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			String username1;
			if (principal instanceof UserDetails) {
				username1 = ((UserDetails) principal).getUsername();
			} else {
				username1 = principal.toString();
			} 
			
			model.addAttribute ("username1",username1 );
			**/
			logger.info("====================================================");
			logger.info("In login controller: " + this.getClass().getName());
			if (error != null) {
				strMsg = strMsg + "/n" + "Bạn nên xem lại tên truy cập hay mật khẩu!";
				model.addAttribute ("message",strMsg );
			}
			
			return "login";
		  }
		 
		
		@RequestMapping(value = "/admin", method = RequestMethod.GET)
		public String adminPage(Model model) {
			logger.info("in admin controller");
		    return "admin";
		}
		
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logoutSuccessfulPage(Model model) {
		    logger.info(" in logout controller");
			model.addAttribute("title", "Logout");
			model.addAttribute("message", "Dang Xuat");
		    return "logout";
		}
		
		@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
		public String userInfo(Model model, Principal principal) {
			 logger.info("in userinfo controller ");
			    String username = principal.getName();
			    String strPrin = principal.toString();
			    logger.info("User Name: "+ username);
			    logger.info("principal: "+ strPrin);
			    model.addAttribute("message", "Thông tin chi tiết về tài khoản");
			    model.addAttribute("userName", username);
			    model.addAttribute("strPrincipal",principal.toString());
		     return "userInfo";
		}
		
		@RequestMapping(value = "/403", method = RequestMethod.GET)
		public String accessDenied(Model model, Principal principal) {
			logger.info( "in 403 controller->>" + principal.getName());
			    if (principal != null) {
			        model.addAttribute("message", "Hi " + principal.getName()
			                + "<br> Bạn không có quyền truy cập vào trang này!");
			    } else {
			        model.addAttribute("message", "Bạn không có quyền truy cập vào trang này!");
			    }
		    return "403";
		}
}
