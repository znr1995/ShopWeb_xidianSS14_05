package com.project_management.shoppingweb.controller.Seller.ViewIncome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Seller/ViewIncome")
public class ViewIncomeHandler {

    @RequestMapping("ViewIncomeHandler")
    public String jumpToViewIncomeMainPage(@ModelAttribute("SellerID")long sellerId, Model model)
    {

        return "/Seller/IncomeManagement";
    }
}
