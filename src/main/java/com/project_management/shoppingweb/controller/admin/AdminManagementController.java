package com.project_management.shoppingweb.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import com.project_management.shoppingweb.domain.*;
import com.project_management.shoppingweb.service.*;
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
import com.project_management.shoppingweb.domain.User;

import com.project_management.shoppingweb.repository.SellerAdvertisementRepository;
import com.project_management.shoppingweb.repository.AdminRepository;
import com.project_management.shoppingweb.repository.PriceRepository;
import com.project_management.shoppingweb.repository.ProductAdvertisementRepository;
import com.project_management.shoppingweb.repository.SellerRepository;

import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.SellerAdvertisementService;
import com.project_management.shoppingweb.service.UserService;
import com.project_management.shoppingweb.service.AdminService;
import com.project_management.shoppingweb.service.PriceService;
import com.project_management.shoppingweb.service.ProductAdvertisementService;


@Controller
@RequestMapping("/admin")
public class AdminManagementController {
	@Autowired
	private PriceService priceService;
	@Autowired

	private SellerService sellerService;
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
	
	@Autowired
	private UserService userService;
	@Resource
	private UserRepository userRepository;
	

	@GetMapping("/backUp")
	public String backUp() {
		return "admin/backUp";
	}
	
	@GetMapping("/customerManage")
	public String custommerManage(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username,Model model) {
		model.addAttribute("adminUserName", username);
		List<User> availableUserList = userService.findAllByState(1);
		List<User> blacklistUserList = userService.findAllByState(2);
		model.addAttribute("availableUserList", availableUserList);
		model.addAttribute("blacklistUserList", blacklistUserList);
		return "admin/customerManage";
	}
	
	@GetMapping("/personalInformation")
	public String personalInformation(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username,Model model) {

		Admin admin = adminService.findByUsername(username);
		model.addAttribute("admin", admin);
		return "admin/personalInformation";
	}

	
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public String updatePrice(HttpServletRequest request,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {

		Price price = new Price();
		price.setAdminId(Long.parseLong(request.getParameter("adminId")));
		price.setProductHighAdvertisementPrice(Double.parseDouble(request.getParameter("productHighAdvertisementPrice")));
		price.setProductLowAdvertisementPrice(Double.parseDouble(request.getParameter("productLowAdvertisementPrice")));
		price.setSellerListAdvertisementPrice(Double.parseDouble(request.getParameter("sellerListAdvertisementPrice")));
		price.setProductRate(Double.parseDouble(request.getParameter("productRate")));
		price.setShopPrice(Double.parseDouble(request.getParameter("shopPrice")));
		priceService.updatePrice(price);
		
    	
		return "redirect:/admin/commission";//返回页面 --
	}
	
	@GetMapping("/agreeShopApply")
	public String agreeApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		

		SellerAdvertisement sellerAdvertisement = sellerAdvertisementService.findById(advertisementId);
		sellerAdvertisement.setStatus(1);
		sellerAdvertisementRepository.save(sellerAdvertisement);
		
		return "redirect:/admin/adsManagement";
	}

	@GetMapping("/rejectShopApply")
	public String rejectApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {

		SellerAdvertisement sellerAdvertisement = sellerAdvertisementService.findById(advertisementId);
		sellerAdvertisementRepository.delete(sellerAdvertisement);
		
		return "redirect:/admin/adsManagement";
	}

	@GetMapping("/rejectProductApply")
	public String rejectProductApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {

		ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		productAdvertisementRepository.delete(productAdvertisement);
		
		return "admin/adsManagement";
	}
	
	@GetMapping("/agreeProductApply")
	public String agreeProductApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {

		ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		productAdvertisement.setStatus(1);
		productAdvertisementRepository.save(productAdvertisement);
		
		return "redirect:/admin/adsManagement";
	}

	@GetMapping("/deleteProductApply")
	public String deleteProductApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {

		ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		productAdvertisementRepository.delete(productAdvertisement);
		return "redirect:/admin/adsManagement";
	}
	
	@GetMapping("/deleteShopApply")
	public String deleteShopApply(@RequestParam("advertisementId") Long advertisementId,Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {

		//ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		SellerAdvertisement sellerAdvertisement = sellerAdvertisementService.findById(advertisementId);
		sellerAdvertisementRepository.delete(sellerAdvertisement);
		return "redirect:/admin/adsManagement";
	}
	
	@GetMapping("/blackCustomer")
	public String blackCustomer(@RequestParam("userId") Long userId,Model model) {
		User user = userService.findByUserId(userId);
		user.setState(2);
		userRepository.save(user);

		List<User> availableUserList = userService.findAllByState(1);
		model.addAttribute("availableFindAll", availableUserList);
		List<User> blacklistUserList = userService.findAllByState(2);
		model.addAttribute("blacklistUserList", blacklistUserList);

		return "redirect:/admin/customerManage";
	}

	@GetMapping("/recoverCustomer")
	public String recoverCustomer(@RequestParam("userId") Long userId,Model model) {
		User user = userService.findByUserId(userId);
		user.setState(1);
		userRepository.save(user);

		List<User> availableUserList = userService.findAllByState(1);
		model.addAttribute("availableFindAll", availableUserList);
		List<User> blacklistUserList = userService.findAllByState(2);
		model.addAttribute("blacklistUserList", blacklistUserList);

		return "redirect:/admin/customerManage";
	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("userId") Long userId,Model model) {
		User user = userService.findByUserId(userId);
		user.setState(0);
		userRepository.save(user);

		List<User> availableUserList = userService.findAllByState(1);
		model.addAttribute("availableFindAll", availableUserList);
		List<User> blacklistUserList = userService.findAllByState(2);
		model.addAttribute("blacklistUserList", blacklistUserList);

		return "redirect:/admin/customerManage";

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

	@GetMapping("/agreeSellerApply")
	public String agreeShopApply(@RequestParam("sellerId") Long sellerId, Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		Seller seller = sellerService.findBySellerId(sellerId);
		seller.setApplyState(1);
		sellerService.save(seller);
		List<Seller> statusList = sellerService.findAllByApplyState(1);
		model.addAttribute("sellerStatusList", statusList);
		//拉出未被拉黑的人
		List<Seller> statusNoList = sellerService.findAllByApplyState(3);
		model.addAttribute("sellerStatusNoList", statusList);
		//拉出被拉黑的人
		List<Seller> applyStatusList = sellerService.findAllByApplyState(2);
		model.addAttribute("sellerApplyStatusList", statusList);
		//拉出未通过审核的商店
		return "redirect:/admin/shopManage";
	}

	@GetMapping("/rejectSellerApply")
	public String rejectShopApply(@RequestParam("sellerId") Long sellerId, Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		Seller seller = sellerService.findBySellerId(sellerId);
		sellerService.delete(seller);
		List<Seller> statusList = sellerService.findAllByApplyState(1);
		model.addAttribute("sellerStatusList", statusList);
		//拉出未被拉黑的人
		List<Seller> statusNoList = sellerService.findAllByApplyState(3);
		model.addAttribute("sellerStatusNoList", statusList);
		//拉出被拉黑的人
		List<Seller> applyStatusList = sellerService.findAllByApplyState(2);
		model.addAttribute("sellerApplyStatusList", statusList);
		//拉出未通过审核的商店
		return "redirect:/admin/shopManage";
	}

	@GetMapping("/black")
	public String black(@RequestParam("sellerId") Long sellerId, Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		Seller seller = sellerService.findBySellerId(sellerId);
		seller.setApplyState(3);
		sellerService.save(seller);
		List<Seller> statusList = sellerService.findAllByApplyState(1);
		model.addAttribute("sellerStatusList", statusList);
		//拉出未被拉黑的人
		List<Seller> statusNoList = sellerService.findAllByApplyState(3);
		model.addAttribute("sellerStatusNoList", statusList);
		//拉出被拉黑的人
		List<Seller> applyStatusList = sellerService.findAllByApplyState(2);
		model.addAttribute("sellerApplyStatusList", statusList);
		//拉出未通过审核的商店
		return "redirect:/admin/shopManage";
	}

	@GetMapping("/unblack")
	public String unblack(@RequestParam("sellerId") Long sellerId, Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {
		Seller seller = sellerService.findBySellerId(sellerId);
		seller.setApplyState(1);
		sellerService.save(seller);
		List<Seller> statusList = sellerService.findAllByApplyState(1);
		model.addAttribute("sellerStatusList", statusList);
		//拉出未被拉黑的人
		List<Seller> statusNoList = sellerService.findAllByApplyState(3);
		model.addAttribute("sellerStatusNoList", statusList);
		//拉出被拉黑的人
		List<Seller> applyStatusList = sellerService.findAllByApplyState(2);
		model.addAttribute("sellerApplyStatusList", statusList);
		//拉出未通过审核的商店
		return "redirect:/admin/shopManage";
	}

	@GetMapping("/commission")
	public String commission(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username,Model model) {
		List<Price> list = priceRepository.findAll();
		if(list.size() == 0) {
			model.addAttribute("price", new Price());
		}else {
			model.addAttribute("price", list.get(list.size()-1));
		}
		Admin admin = adminService.findByUsername(username);
		model.addAttribute("adminId", admin.getAdminId());
		model.addAttribute("adminUserName", username);

		List<ProductAdvertisement> advertisementList =  productAdvertisementService.findAllByStatus(1);
		model.addAttribute("advertisementList", advertisementList);
		
		return "admin/commission";
	}
	@GetMapping("/search")
	public String search(@RequestParam("shopname") String shopname, Model model,@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username) {

		List<Seller> searchList = sellerService.findAllByShopnameLike(shopname);
		model.addAttribute("searchList", searchList);
		List<Seller> statusList = sellerService.findAllByApplyState(1);
		model.addAttribute("sellerStatusList", statusList);
		//拉出未被拉黑的人
		List<Seller> statusNoList = sellerService.findAllByApplyState(3);
		model.addAttribute("sellerStatusNoList", statusList);
		//拉出被拉黑的人
		List<Seller> applyStatusList = sellerService.findAllByApplyState(2);
		model.addAttribute("sellerApplyStatusList", statusList);
		//拉出未通过审核的商店
		return "/admin/shopManageSearch";
	}
}
