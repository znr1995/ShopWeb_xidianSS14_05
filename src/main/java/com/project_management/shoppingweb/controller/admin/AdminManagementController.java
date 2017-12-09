package com.project_management.shoppingweb.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project_management.shoppingweb.domain.Price;
import com.project_management.shoppingweb.repository.PriceRepository;
import com.project_management.shoppingweb.service.PriceService;

@Controller
@RequestMapping("/admin")
public class AdminManagementController {
	@Autowired
	private PriceService priceService;
	
	@Resource
	private PriceRepository priceRepository;
	
	@RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
	public String updatePrice(HttpServletRequest request,Model model) {
		Price price = new Price();
		price.setAdminId(Long.parseLong(request.getParameter("adminId")));
		price.setAdvertisementHighPrice(Double.parseDouble(request.getParameter("advertisementHighPrice")));
		price.setAdvertisementLowPrice(Double.parseDouble(request.getParameter("advertisementLowPrice")));
		price.setProductRate(Double.parseDouble(request.getParameter("productRate")));
		priceService.updatePrice(price);
		
		model.addAttribute("price", price);
		return "";//返回页面 -- 
	}
}
