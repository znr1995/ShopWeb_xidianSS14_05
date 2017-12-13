package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Address;
import com.project_management.shoppingweb.service.AddressService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BuyController {
    @Autowired
    private AddressService addressService;
    @RequestMapping(value = "/Buy",method = RequestMethod.GET)
    public String Buy(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShopID = request.getParameter("ShopID");
        String ProductID = request.getParameter("ProductID");
        String UnitPrice = request.getParameter("UnitPrice");


        if(UserID.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/productdetial";
        }
        String ProductAmount = request.getParameter("ProductAmount");

        if(ProductAmount.equals("")||UnitPrice.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/productdetial";
        }
        if(Integer.parseInt(ProductAmount)<=0||Double.valueOf(UnitPrice)<=0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/productdetial";
        }

        double UP = Double.valueOf(UnitPrice);
        double PA = Double.valueOf(ProductAmount);
        double t = UP * PA;
        String Total = String.valueOf(t);


        model.addAttribute("UserID", UserID);
        model.addAttribute("ProductID", ProductID);
        model.addAttribute("ShopID", ShopID);
        model.addAttribute("UnitPrice", UnitPrice);
        model.addAttribute("ProductAmount", ProductAmount);
        model.addAttribute("Total", Total);

        List<Address> AddressList = new ArrayList<Address>();
        AddressList = addressService.findAllByUserId(Long.parseLong(UserID));
        if(AddressList.size() == 0)
            return "/User/Pay";
        model.addAttribute("AddressList", AddressList);

//        List<String> CityList = new ArrayList<String>();
//        List<String> DetailAddressList = new ArrayList<String>();
//        List<String> District = new ArrayList<String>();
//        List<String> IsDefaultAddressList = new ArrayList<String>();
//        List<String> TelList = new ArrayList<String>();
//        List<String> PostCodeList = new ArrayList<String>();
//        List<String> ProvinceList = new ArrayList<String>();
//        for(int i = 0; i < AddressList.size(); i++)
//        {
//            CityList.add(AddressList.get(i).getCity());
//            DetailAddressList.add(AddressList.get(i).getDetailAddress());
//            District.add(AddressList.get(i).getDistrict());
//            if(AddressList.get(i).getIsDefaultAddress() == false)
//                IsDefaultAddressList.add("0");
//            IsDefaultAddressList.add("1");
//            TelList.add(AddressList.get(i).getTel());
//            PostCodeList.add(String.valueOf(AddressList.get(i).getPostcode()));
//            ProvinceList.add(AddressList.get(i).getProvince());
//        }
//        model.addAttribute("CityList", CityList);
//        model.addAttribute("DetailAddressList", DetailAddressList);
//        model.addAttribute("District", District);
//        model.addAttribute("IsDefaultAddressList", IsDefaultAddressList);
//        model.addAttribute("TelList", TelList);
//        model.addAttribute("PostCodeList", PostCodeList);
//        model.addAttribute("ProvinceList", ProvinceList);
        return "/User/Pay";
    }
}
