package com.project_management.shoppingweb.controller.Seller.ModifySellerAdvertisement;

import com.project_management.shoppingweb.service.SellerSQLFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

@Controller
@RequestMapping("Seller/ModifyShellAdverisement")
public class ModifySellerAdverisementHandler {

    private long sellerID = -1;
    private SellerSQLFunction sellerSQLFunction = new SellerSQLFunction();

    @RequestMapping("ModifySellerAdverisementHandler")
    public String jumpToModifySellerAdvertisementMainPage(@ModelAttribute("SellerID")long sellerId, Model model)
    {
        sellerID = sellerId;
        LinkedList<String> advertisements = sellerSQLFunction.getSellerAdvertisement(sellerID);
        //TODO:使用linkedlist是否有问题
        model.addAttribute("Advertisements",advertisements);
        return "ModifySellerAdverMainPage";
    }

    @RequestMapping("ModifySellerAdverisement")
    public String modifySellerAdverisement(HttpServletRequest request,RedirectAttributes attribute)
    {
        String[] advertisement = request.getParameterValues("Advertisements");
        LinkedList<String> advertisements = new LinkedList<String>();
        for(String str: advertisement)
        {
            advertisements.add(str);
        }
        attribute.addAttribute("Seller",sellerID);
        if(sellerSQLFunction.writeInAdvertisment(sellerID,advertisements))
            return "redirect:/Seller/Main";
        else
            //TODO:error handler
            return "error/handler";

    }

    @RequestMapping("Cancel")
    public String jumpToMainPage(RedirectAttributes attributes)
    {
        attributes.addAttribute("Seller",sellerID);
        return "redirect:/Seller/Main";
    }

    @RequestMapping("ReturnBack")
    public String returnToMainPage(RedirectAttributes attributes)
    {
        attributes.addAttribute("Seller",sellerID);
        return "redirect:/Seller/Main";
    }
}
