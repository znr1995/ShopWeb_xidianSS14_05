package com.project_management.shoppingweb.controller.Seller.ViewIncome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Seller")
public class ViewIncomeHandler {

    @RequestMapping("ViewIncomeHandler")
    public String jumpToViewIncomeMainPage(long Id, Model model)
    {
        //TODO:增加此模块的主页信息
        return "ViewIncomeMainPage";
    }
}
