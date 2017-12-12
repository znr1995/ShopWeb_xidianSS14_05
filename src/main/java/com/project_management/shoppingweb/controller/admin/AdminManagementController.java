package com.project_management.shoppingweb.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project_management.shoppingweb.domain.Advertisement;
import com.project_management.shoppingweb.domain.Price;
import com.project_management.shoppingweb.domain.ProductAdvertisement;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.repository.AdvertisementRepository;
import com.project_management.shoppingweb.repository.PriceRepository;
import com.project_management.shoppingweb.repository.ProductAdvertisementRepository;
import com.project_management.shoppingweb.repository.SellerRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import com.project_management.shoppingweb.service.AdvertisementService;
import com.project_management.shoppingweb.service.PriceService;
import com.project_management.shoppingweb.service.ProductAdvertisementService;
import com.project_management.shoppingweb.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminManagementController {
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private AdvertisementService advertisementService;
	@Resource
	private ProductAdvertisementRepository productAdvertisementRepository;
	
	@Resource
	private PriceRepository priceRepository;
	@Resource
	private SellerRepository sellerRepository;
	@Resource
	private AdvertisementRepository advertisementRepository;
	@Autowired
	private ProductAdvertisementService productAdvertisementService;
	
	@Autowired
	private UserService userService;
	@Resource
	private UserRepository userRepository;
	
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
	public String personalInformation() {
		return "admin/personalInformation";
	}
	
	@GetMapping("/shopManage")
	public String shopManage() {
		return "admin/shopManage";
	}
	
	
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public String updatePrice(HttpServletRequest request,Model model) {
		Price price = new Price();
		price.setAdminId(Long.parseLong(request.getParameter("adminId")));
		price.setAdvertisementHighPrice(Double.parseDouble(request.getParameter("advertisementHighPrice")));
		price.setAdvertisementLowPrice(Double.parseDouble(request.getParameter("advertisementLowPrice")));
		price.setProductRate(Double.parseDouble(request.getParameter("productRate")));
		priceService.updatePrice(price);
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
		return "redirect:/admin/adsManagement";//返回页面 -- 
	}
	
	@GetMapping("/agreeShopApply")
	public String agreeApply(@RequestParam("advertisementId") Long advertisementId,Model model) {
		
		Advertisement advertisement = advertisementService.findById(advertisementId);
		advertisement.setStatus(1);
		advertisementRepository.save(advertisement);
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
		return "redirect:/admin/adsManagement";
	}
	
	@GetMapping("/rejectShopApply")
	public String rejectApply(@RequestParam("advertisementId") Long advertisementId,Model model) {
		
		Advertisement advertisement = advertisementService.findById(advertisementId);
		advertisementRepository.delete(advertisement);
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
		return "redirect:/admin/adsManagement";
	}
	
	@GetMapping("/rejectProductApply")
	public String rejectProductApply(@RequestParam("advertisementId") Long advertisementId,Model model) {
		
//		Advertisement advertisement = advertisementService.findById(advertisementId);
//		advertisementRepository.delete(advertisement);
		ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		productAdvertisementRepository.delete(productAdvertisement);
		
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
	
	@GetMapping("/agreeProductApply")
	public String agreeProductApply(@RequestParam("advertisementId") Long advertisementId,Model model) {
		ProductAdvertisement productAdvertisement = productAdvertisementService.findByAdvertisementId(advertisementId);
		productAdvertisement.setStatus(1);
		productAdvertisementRepository.save(productAdvertisement);
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
		return "redirect:/admin/adsManagement";
	}
	
	@GetMapping("/blackCustomer")
	public String blackCustomer(@RequestParam("userid") Long userid,Model model) {
		User user = userService.findByUserid(userid);
		user.setStatus(2);
		userRepository.save(user);
		
		List<User> availableUserList = userService.findAllByStatus(1);
		model.addAttribute("availableFindAll", availableUserList);
		List<User> blacklistUserList = userService.findAllByStatus(2);
		model.addAttribute("blacklistUserList", availableUserList);
		
		return "redirect:/admin/customerManage";
	}
	
	@GetMapping("/recoverCustomer")
	public String recoverCustomer(@RequestParam("userid") Long userid,Model model) {
		User user = userService.findByUserid(userid);
		user.setStatus(1);
		userRepository.save(user);
		
		List<User> availableUserList = userService.findAllByStatus(1);
		model.addAttribute("availableFindAll", availableUserList);
		List<User> blacklistUserList = userService.findAllByStatus(2);
		model.addAttribute("blacklistUserList", availableUserList);
		
		return "redirect:/admin/customerManage";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("userid") Long userid,Model model) {
		User user = userService.findByUserid(userid);
		user.setStatus(0);
		userRepository.save(user);
		
		List<User> availableUserList = userService.findAllByStatus(1);
		model.addAttribute("availableFindAll", availableUserList);
		List<User> blacklistUserList = userService.findAllByStatus(2);
		model.addAttribute("blacklistUserList", availableUserList);
		
		return "redirect:/admin/customerManage";
	}
}
