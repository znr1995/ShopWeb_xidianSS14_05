package com.project_management.shoppingweb.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.project_management.shoppingweb.config.WebSecurityConfig;
import com.project_management.shoppingweb.domain.Admin;
import com.project_management.shoppingweb.domain.Price;
import com.project_management.shoppingweb.domain.ProductAdvertisement;
import com.project_management.shoppingweb.domain.SellerAdvertisement;
import com.project_management.shoppingweb.repository.SellerAdvertisementRepository;
import com.project_management.shoppingweb.repository.AdminRepository;
import com.project_management.shoppingweb.repository.PriceRepository;
import com.project_management.shoppingweb.repository.ProductAdvertisementRepository;
import com.project_management.shoppingweb.repository.SellerRepository;
import com.project_management.shoppingweb.service.SellerAdvertisementService;
import com.project_management.shoppingweb.service.AdminService;
import com.project_management.shoppingweb.service.PriceService;
import com.project_management.shoppingweb.service.ProductAdvertisementService;

@Controller
@RequestMapping("/admin")
public class AdminManagementController {
	@Autowired
	private PriceService priceService;
	@Autowired
	private SellerAdvertisementService sellerAdvertisementService;
	@Autowired
	private ProductAdvertisementService productAdvertisementService;
	@Resource
	private ProductAdvertisementRepository productAdvertisementRepository;
	@Autowired
	private AdminService adminService;
	@Resource
	private PriceRepository priceRepository;
	@Resource
	private SellerRepository sellerRepository;
	@Resource
	private SellerAdvertisementRepository sellerAdvertisementRepository;
	@Resource 
	private AdminRepository adminRepository;
	
	
	@GetMapping("/backUp")
	public String backUp() {
		return "admin/backUp";
	}
	
	@GetMapping("/commission")
	public String commission() {
		return "admin/commission";
	}
	
	@GetMapping("/customerManage")
	public String custommerManage() {
		return "admin/customerManage";
	}
	
	@GetMapping("/personalInformation")
	public String personalInformation(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username,Model model) {
		Admin admin = adminService.findByUsername(username);
		model.addAttribute("admin", admin);
		return "admin/personalInformation";
	}
	
	@GetMapping("/shopManage")
	public String shopManage() {
		return "admin/shopManage";
	}
	
	
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public String updatePrice(HttpServletRequest request,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		Price price = new Price();
		price.setAdminId(Long.parseLong(request.getParameter("adminId")));
		price.setProductHighAdvertisementPrice(Double.parseDouble(request.getParameter("productHighAdvertisementPrice")));
		price.setProductLowAdvertisementPrice(Double.parseDouble(request.getParameter("productLowAdvertisementPrice")));
		price.setSellerListAdvertisementPrice(Double.parseDouble(request.getParameter("sellerListAdvertisementPrice")));
		price.setProductRate(Double.parseDouble(request.getParameter("productRate")));
		priceService.updatePrice(price);
		
    	List<SellerAdvertisement> sellerAdvertisementList = sellerAdvertisementService.findAllByStatus(0);
		model.addAttribute("shopFindAll", sellerAdvertisementList);
		
		List<ProductAdvertisement> productAdvertisementList = productAdvertisementService.findAllByStatus(0);
		model.addAttribute("productFindAll", productAdvertisementList);
		
    	List<SellerAdvertisement> onAdvertisementList = sellerAdvertisementService.findAllByStatus(1);
		model.addAttribute("onShopFindAll", onAdvertisementList);
		
		List<ProductAdvertisement> onProductAdvertisementList = productAdvertisementService.findAllByStatus(1);
		model.addAttribute("onProductFindAll", onProductAdvertisementList);
		
		List<Price> list = priceRepository.findAll();
    	if(list.size() == 0) {
    		model.addAttribute("price", new Price());
    	}else {
    		model.addAttribute("price", list.get(list.size()-1));
    	}
    	
    	
    	Admin admin = adminService.findByUsername(username);
    	model.addAttribute("adminId", admin.getAdminId());
		model.addAttribute("adminUserName", username);
		return "redirect:/admin/adsManagement";//返回页面 -- 
	}
	
	@GetMapping("/agreeShopApply")
	public String agreeApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		
		SellerAdvertisement sellerAdvertisement = sellerAdvertisementService.findById(advertisementId);
		sellerAdvertisement.setStatus(1);
		sellerAdvertisementRepository.save(sellerAdvertisement);
		List<Price> list = priceRepository.findAll();
    	List<SellerAdvertisement> sellerAdvertisementList = sellerAdvertisementService.findAllByStatus(0);
		model.addAttribute("shopFindAll", sellerAdvertisementList);
		
		List<ProductAdvertisement> productAdvertisementList = productAdvertisementService.findAllByStatus(0);
		model.addAttribute("productFindAll", productAdvertisementList);
		
    	List<SellerAdvertisement> onSellerAdvertisementList = sellerAdvertisementService.findAllByStatus(1);
		model.addAttribute("onShopFindAll", onSellerAdvertisementList);
		
		List<ProductAdvertisement> onProductAdvertisementList = productAdvertisementService.findAllByStatus(1);
		model.addAttribute("onProductFindAll", onProductAdvertisementList);
		
    	model.addAttribute("adminId", list.get(list.size()-1).getAdminId());
    	model.addAttribute("price", list.get(list.size()-1));
		return "redirect:/admin/adsManagement";
	}
	
	@GetMapping("/rejectShopApply")
	public String rejectApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		
		SellerAdvertisement sellerAdvertisement = sellerAdvertisementService.findById(advertisementId);
		sellerAdvertisementRepository.delete(sellerAdvertisement);
		List<Price> list = priceRepository.findAll();
    	if(list.size() == 0) {
    		model.addAttribute("price", new Price());
    	}else {
    		model.addAttribute("price", list.get(list.size()-1));
    	}
    	
    	
    	Admin admin = adminService.findByUsername(username);
    	model.addAttribute("adminId", admin.getAdminId());
		model.addAttribute("adminUserName", username);
    	List<SellerAdvertisement> sellerAdvertisementList = sellerAdvertisementService.findAllByStatus(0);
		model.addAttribute("shopFindAll", sellerAdvertisementList);
		
		List<ProductAdvertisement> productAdvertisementList = productAdvertisementService.findAllByStatus(0);
		model.addAttribute("productFindAll", productAdvertisementList);
		
    	List<SellerAdvertisement> onAdvertisementList = sellerAdvertisementService.findAllByStatus(1);
		model.addAttribute("onShopFindAll", onAdvertisementList);
		
		List<ProductAdvertisement> onProductAdvertisementList = productAdvertisementService.findAllByStatus(1);
		model.addAttribute("onProductFindAll", onProductAdvertisementList);
		
  
		return "redirect:/admin/adsManagement";
	}
	
	@GetMapping("/rejectProductApply")
	public String rejectProductApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		
//		Advertisement advertisement = advertisementService.findById(advertisementId);
//		advertisementRepository.delete(advertisement);
		ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		productAdvertisementRepository.delete(productAdvertisement);
		
		List<Price> list = priceRepository.findAll();
    	if(list.size() == 0) {
    		model.addAttribute("price", new Price());
    	}else {
    		model.addAttribute("price", list.get(list.size()-1));
    	}
    	
    	
    	Admin admin = adminService.findByUsername(username);
    	model.addAttribute("adminId", admin.getAdminId());
		model.addAttribute("adminUserName", username);
    	List<SellerAdvertisement> sellerAdvertisementList = sellerAdvertisementService.findAllByStatus(0);
		model.addAttribute("shopFindAll", sellerAdvertisementList);
		
		List<ProductAdvertisement> productAdvertisementList = productAdvertisementService.findAllByStatus(0);
		model.addAttribute("productFindAll", productAdvertisementList);
		
    	List<SellerAdvertisement> onSellerAdvertisementList = sellerAdvertisementService.findAllByStatus(1);
		model.addAttribute("onShopFindAll", onSellerAdvertisementList);
		
		List<ProductAdvertisement> onProductAdvertisementList = productAdvertisementService.findAllByStatus(1);
		model.addAttribute("onProductFindAll", onProductAdvertisementList);
		
		return "admin/adsManagement";
	}
	
	@GetMapping("/agreeProductApply")
	public String agreeProductApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		productAdvertisement.setStatus(1);
		productAdvertisementRepository.save(productAdvertisement);
		List<Price> list = priceRepository.findAll();
    	if(list.size() == 0) {
    		model.addAttribute("price", new Price());
    	}else {
    		model.addAttribute("price", list.get(list.size()-1));
    	}
    	
    	
    	Admin admin = adminService.findByUsername(username);
    	model.addAttribute("adminId", admin.getAdminId());
		model.addAttribute("adminUserName", username);
    	List<SellerAdvertisement> sellerAdvertisementList = sellerAdvertisementService.findAllByStatus(0);
		model.addAttribute("shopFindAll", sellerAdvertisementList);
		
		List<ProductAdvertisement> productAdvertisementList = productAdvertisementService.findAllByStatus(0);
		model.addAttribute("productFindAll", productAdvertisementList);
		
    	List<SellerAdvertisement> onAdvertisementList = sellerAdvertisementService.findAllByStatus(1);
		model.addAttribute("onShopFindAll", onAdvertisementList);
		
		List<ProductAdvertisement> onProductAdvertisementList = productAdvertisementService.findAllByStatus(1);
		model.addAttribute("onProductFindAll", onProductAdvertisementList);
		
    	
		return "redirect:/admin/adsManagement";
	}
	
	
	
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	public String updataAdmin(HttpServletRequest request,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		Admin admin = adminService.findByUsername(username);
		admin.setTel(request.getParameter("tel"));
		admin.setEmail(request.getParameter("email"));
		adminRepository.save(admin);
		model.addAttribute("admin", admin);
		return "redirect:/admin/personalInformation";
	}
}
