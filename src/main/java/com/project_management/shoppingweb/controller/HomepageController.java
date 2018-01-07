package com.project_management.shoppingweb.controller;

import java.util.*;

import com.project_management.shoppingweb.domain.ProductAdvertisement;
import com.project_management.shoppingweb.domain.SellerAdvertisement;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductAdvertisementService;
import com.project_management.shoppingweb.service.User.User_SellerAdvertisementService;
import com.project_management.shoppingweb.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomepageController {


    @Autowired
    private User_SellerAdvertisementService userSellerAdvertisementService;
    @Autowired
    private User_ProductAdvertisementService userProductAdvertisementService;
    @Autowired
    private UserService userService;

    private long UserID = -1;

    private Logger logger = Logger.getLogger(this.getClass());


    @RequestMapping("/User/login")
    public String login(){return "/User/loginNew";}
    @RequestMapping("/User/register")
    public String register(){return "/User/registerNew";}

    @RequestMapping("/User/Main")
    public String userMain(@ModelAttribute("UserID")long userId, Model model, RedirectAttributes attributes)
    {
        //登录逻辑处理
        UserID = userId;
        return "redirect:/homepage";
    }

    @RequestMapping("/signout")
    public  String signout(){
        //登出逻辑处理
        UserID = -1;
        return "redirect:/homepage";
    }

    @RequestMapping(value = "/homepage")
    public String homepage(Model model){
        Date currentDate = new Date();
        List<SellerAdvertisement> seller_ads = userSellerAdvertisementService.findAllByStatus(1);
        List<SellerAdvertisement> del_seller_adv = new ArrayList<>();
        for(SellerAdvertisement sa : seller_ads){
            if(currentDate.after(sa.getEndDate())){
                del_seller_adv.add(sa);
            }
        }
        seller_ads.removeAll(del_seller_adv);
        boolean seller_ads_isnull = false;
        if(seller_ads == null || seller_ads.isEmpty())  seller_ads_isnull=true;
        model.addAttribute("shop_ads", seller_ads);//返回商铺广告的list，前端解析
        model.addAttribute("seller_ad_isnull", seller_ads_isnull);

        List<ProductAdvertisement> pro_ads_roll = userProductAdvertisementService.findAllByType(1, 1);
        List<ProductAdvertisement> del_product_adv1 = new ArrayList<>();
        for(ProductAdvertisement pa : pro_ads_roll){
            if(currentDate.after(pa.getEndDate())){
                del_product_adv1.add(pa);
            }
        }
        pro_ads_roll.removeAll(del_product_adv1);
        boolean pro_rollad_isnull = false;
        if(pro_ads_roll == null || pro_ads_roll.isEmpty())  pro_rollad_isnull=true;//如果为空设为true
        model.addAttribute("pro_ads_roll",pro_ads_roll);//动态轮转商品广告
        model.addAttribute("pro_rollad_isnull", pro_rollad_isnull);

        List<ProductAdvertisement> pro_ads_list = userProductAdvertisementService.findAllByType(2, 1);
        List<ProductAdvertisement> del_Product_adv2 = new ArrayList<>();
        for(ProductAdvertisement pa : pro_ads_list){
            if(currentDate.after(pa.getEndDate())){
                del_Product_adv2.add(pa);
            }
        }
        pro_ads_list.removeAll(del_Product_adv2);
        boolean pro_listad_isnull = false;
        if(pro_ads_list == null || pro_ads_list.isEmpty())  pro_listad_isnull=true;//如果为空设为true
        model.addAttribute("pro_ads", pro_ads_list);//商品广告
        model.addAttribute("pro_listad_isnull", pro_listad_isnull);
        model.addAttribute("UserID",UserID);
        if(UserID == -1){
            model.addAttribute("UserName", "UserName");
        }
        else{
            User user = userService.findByUserId(UserID);
            model.addAttribute("UserName", user.getUsername());
        }
        return "/Homepage/homepage1";
    }
}
