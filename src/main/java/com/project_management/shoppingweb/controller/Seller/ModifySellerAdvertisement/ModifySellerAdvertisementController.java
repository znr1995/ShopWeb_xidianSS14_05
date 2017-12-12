package com.project_management.shoppingweb.controller.Seller.ModifySellerAdvertisement;



import com.project_management.shoppingweb.domain.SellerAdvertisement;
import com.project_management.shoppingweb.service.Seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/Seller/ModifySellerAdvertisement")
public class ModifySellerAdvertisementController {

    @Autowired
    private SellerService sellerService;

    private long sellerID = -1;

    @RequestMapping("ModifySellerAdvertisementHandler")
    public String jumpToModifySellerAdvertisementMainPage(@ModelAttribute("SellerID")long sellerId, Model model)
    {
        sellerID = sellerId;
        List<SellerAdvertisement> advertisements = sellerService.getSellerAdvertisements(sellerID);
        //TODO:获取到广告,需要修改广告界面
        model.addAttribute("Advertisements",advertisements);
        return "/Seller/ModifySellerAdverMainPage";
    }

    @RequestMapping(value = "ModifySellerAdvertisement",params = "action=OkButton")
    public String modifySellerAdvertisement(HttpServletRequest request, RedirectAttributes attribute)
    {
        //TODO:从广告界面获取广告
        String[] advertisement = request.getParameterValues("Advertisements");
        LinkedList<SellerAdvertisement> advertisements = new LinkedList<SellerAdvertisement>();

        attribute.addAttribute("SellerID",sellerID);
        if(sellerService.writeInSellerAdvertisements(advertisements))
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

