package com.project_management.shoppingweb.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.project_management.shoppingweb.config.WebSecurityConfig;
import com.project_management.shoppingweb.domain.Admin;
import com.project_management.shoppingweb.domain.Advertisement;
import com.project_management.shoppingweb.domain.Price;
import com.project_management.shoppingweb.domain.ProductAdvertisement;
import com.project_management.shoppingweb.repository.AdminRepository;
import com.project_management.shoppingweb.repository.PriceRepository;
import com.project_management.shoppingweb.service.AdminService;
import com.project_management.shoppingweb.service.AdvertisementService;
import com.project_management.shoppingweb.service.PriceService;
import com.project_management.shoppingweb.service.ProductAdvertisementService;
import com.project_management.shoppingweb.util.MD5Util;

@Controller
@RequestMapping("/admin")
public class AdminLoinController {

	public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private ProductAdvertisementService productAdvertisementService;
	
	@Resource
	private AdminRepository adminRepository;
	@Resource
	private PriceRepository priceRepository;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	//index页面  
    @GetMapping("/adsManagement")  
    public String adsManagement(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username,Model model) {  
    	//Sort sort = new Sort(Sort.Direction.DESC,"createTime"); 
    	//Pageable pageable = new PageRequest(0,5,sort);
    	List<Price> list = priceRepository.findAll();
    	List<Advertisement> advertisementList = advertisementService.findAllByStatus(0);
		model.addAttribute("shopFindAll", advertisementList);
		
		List<ProductAdvertisement> productAdvertisementList = productAdvertisementService.findAllByStatus(0);
		model.addAttribute("productFindAll", productAdvertisementList);
		
    	List<Advertisement> onAdvertisementList = advertisementService.findAllByStatus(1);
		model.addAttribute("onShopFindAll", onAdvertisementList);
		
		List<ProductAdvertisement> onProductAdvertisementList = productAdvertisementService.findAllByStatus(1);
		model.addAttribute("onProductFindAll", onProductAdvertisementList);
		
    	model.addAttribute("adminId", list.get(list.size()-1).getAdminId());
    	model.addAttribute("price", list.get(list.size()-1));
        return "admin/adsManagement";  
    }  
  
    //注册页面  
    @GetMapping("/register")  
    public String register(){  
        return "admin/register";  
    }  
  
    //登录页面  
    @GetMapping("/login")  
    public String login(){  
        return "admin/login";  
    }  
	
	@RequestMapping(value = "/addlogin", method = RequestMethod.POST)
	public String addlogin(HttpServletRequest request,HttpSession session,Model model)  throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		 Map<String, Object> map = new HashMap<>();
			Admin admin = adminService.findByUsername(username, getPwd(password));
			if(admin == null) {
//				 map.put("success", false);
//		            map.put("message", "密码错误");
		            return "admin/login";
			}
			//设置session
		session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
//        map.put("success", true);
//        map.put("message", "登录成功");
		model.addAttribute("adminId", admin.getAdminId());
        return "redirect:/admin/adsManagement";
		
	}
	
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        // 移除session
	        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
	        return "redirect:/admin/login";
	    }
	 
	 @RequestMapping(value = "/addregister", method = RequestMethod.POST)
	 public String addregist(HttpServletRequest request){
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String password2 = request.getParameter("password2");
		 String tel = request.getParameter("tel");
		 //Map<String, Object> map = new HashMap<>();
		 Admin admin = adminService.findByUsername(username);
		 /*
		  * 最好前端做判断
		  * */
		 if(!password.equals(password2)) {
			// map.put("success", false);
	       //  map.put("message", "两次密码不相同");
	         return "/admin/register";
		 }
		 
		 if(admin != null) {
			// map.put("success", false);
	       //  map.put("message", "用户已存在");
	         return "/admin/register";
		 }
		 
		 admin = new Admin();
		 
		 admin.setUsername(username);
		 admin.setPassword(getPwd(password));
		 admin.setTel(tel);
		 
		 adminRepository.save(admin);
//		 map.put("success", true);
//	     map.put("message", "注册成功");
	     return "/admin/login";
	 }
	 
	 
	 private String getPwd(String password){
	    	try {
	    		String pwd = MD5Util.encrypt(password+PASSWORD_KEY);
	    		return pwd;
			} catch (Exception e) {
				logger.error("密码加密异常：",e);
			}
	    	return null;
	    }

}
