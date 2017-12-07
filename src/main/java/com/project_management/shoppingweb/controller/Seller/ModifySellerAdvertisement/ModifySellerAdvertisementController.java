package com.project_management.shoppingweb.controller.Seller.ModifySellerAdvertisement;



import com.project_management.shoppingweb.service.Seller.SellerSQLFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

@Controller
@RequestMapping("/Seller/ModifySellerAdvertisement")
public class ModifySellerAdvertisementController {
    private int sellerID = -1;

    @RequestMapping("ModifySellerAdvertisementHandler")
    public String jumpToModifySellerAdvertisementMainPage(@ModelAttribute("SellerID")int sellerId, Model model)
    {
        sellerID = sellerId;
        LinkedList<String> advertisements = SellerSQLFunction.getInstance().getSellerAdvertisement(sellerID);
        model.addAttribute("Advertisements",advertisements);
        return "/Seller/ModifySellerAdverMainPage";
    }

    @RequestMapping(value = "ModifySellerAdvertisement",params = "action=OkButton")
    public String modifySellerAdvertisement(HttpServletRequest request, RedirectAttributes attribute)
    {
        //获取不到Advertisements的值
        String[] advertisement = request.getParameterValues("Advertisements");
        LinkedList<String> advertisements = new LinkedList<String>();
        for(String str: advertisement)
        {
            advertisements.add(str);
        }
        attribute.addAttribute("SellerID",sellerID);
        if(SellerSQLFunction.getInstance().writeInAdvertisement(sellerID,advertisements))
            return "redirect:/Seller/Main";
        else
        {
            attribute.addAttribute("errorMessage","add advertisements fail");
            return "/error/handler";
        }
    }

    @RequestMapping(value = "ModifySellerAdvertisement",params = "action=CancelButton")
    public String jumpToMainPage(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }

    @RequestMapping("ReturnBack")
    public String returnToMainPage(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }
}

