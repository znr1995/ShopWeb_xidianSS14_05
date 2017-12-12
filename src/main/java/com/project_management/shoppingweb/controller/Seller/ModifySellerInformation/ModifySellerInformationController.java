package com.project_management.shoppingweb.controller.Seller.ModifySellerInformation;


import com.project_management.shoppingweb.domain.Seller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Seller/ModifySellerInformation")
public class ModifySellerInformationController {

    private  long sellerID = -1;

    @RequestMapping("ModifySellerInformationHandler")
    public String jumpToModifySellerInformationPage(@ModelAttribute("SellerID")long sellerId, Model model)
    {
        sellerID = sellerId;
        Seller seller = SellerSQLFunction.getInstance().getSellerInformation(sellerID);
        model.addAttribute("Sculpture",seller.getSculpture());
        model.addAttribute("Address",seller.getAddress());
        model.addAttribute("Email",seller.getEmail());
        model.addAttribute("Passwd",seller.getPassword());
        model.addAttribute("PhoneNum",seller.getPhoneNum());
        model.addAttribute("Username",seller.getUsername());
        model.addAttribute("SellerID",sellerID);
        return "/Seller/ModifySellerInformationPage";
    }

    @RequestMapping("ModifySellerInformation")
    public String modifyFiveItem(@RequestParam(value = "SellerID",required = true)long userId,
                                 @RequestParam(value = "Username",required = true)String userName,
                                 @RequestParam(value = "Email",required = true)String email,
                                 @RequestParam(value = "Passwd",required = true)String passwd,
                                 @RequestParam(value = "PhoneNum",required = true)String phoneNum,
                                 @RequestParam(value = "Address",required = true)String address,
                                 @RequestParam(value = "Sculpture",required = true)String sculpture,
                                 RedirectAttributes attributes

    ){

        Seller newSeller = new Seller();
        newSeller.setAddress(address);
        newSeller.setEmail(email);
        newSeller.setPassword(passwd);
        newSeller.setPhoneNum(phoneNum);
        newSeller.setSculpture(sculpture);
        newSeller.setUsername(userName);
        newSeller.setSellerId(sellerID);
        attributes.addAttribute("SellerID",sellerID);
        if(SellerSQLFunction.getInstance().writeInInformation(newSeller))
            return "redirect:/Seller/Main";
        else
        {
            attributes.addAttribute("errorMassage","modify seller information fail");
            return "redirect:/error/errorHandler";
        }
    }

    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }
}
