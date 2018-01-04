package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.*;
import com.project_management.shoppingweb.service.AddressService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import com.project_management.shoppingweb.service.User.User_TradeDetailService;
import com.project_management.shoppingweb.service.User.User_TradeService;
import com.project_management.shoppingweb.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PayController {
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private User_TradeDetailService tradeDetailService;
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private User_ProductService productService;


    @RequestMapping(value = "/Pay",params = "Pay=Pay")
    public String Pay(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShopID = request.getParameter("ShopID");
        String ProductID = request.getParameter("ProductID");
        String UnitPrice = request.getParameter("UnitPrice");
        String ProductAmount = request.getParameter("ProductAmount");
        String Total = request.getParameter("Total");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String PayWay = request.getParameter("PayWay");
        String SellerName = request.getParameter("SellerName");
        String ProductName = request.getParameter("ProductName");
        List<Address> AddressList = new ArrayList<Address>();
        AddressList = addressService.findAllByUserId(Long.parseLong(UserID));
        int number = AddressList.size();

        model.addAttribute("UserName", userService.findByUserId(Long.parseLong(UserID)).getUsername());

        Product product = productService.findProductByProductID(Long.parseLong(ProductID));
        if(product == null){
            model.addAttribute("UserID", UserID);
            model.addAttribute("UserName", userService.findByUserId(Long.parseLong(UserID)).getUsername());
            return "/User/productoops";
        }
        if(Integer.parseInt(ProductAmount) > product.getProductStock()){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
            if(AddressList.size() == 0)
                return "/User/PayNew";
            model.addAttribute("AddressList",AddressList);
            return "/User/PayNew";
        }

        if(account.equals("") || password.equals("") || address.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
            if(AddressList.size() == 0)
                return "/User/PayNew";
            model.addAttribute("AddressList",AddressList);
            return "/User/PayNew";
        }

        try {
            int a = Integer.parseInt(address);
        }
        catch (Exception e){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
            if(AddressList.size() == 0)
                return "/User/PayNew";
            model.addAttribute("AddressList",AddressList);
            return "/User/PayNew";
        }

        int b = Integer.parseInt(address);

        if(b > number || Integer.parseInt(address)<= 0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
            if(AddressList.size() == 0)
                return "/User/PayNew";
            model.addAttribute("AddressList",AddressList);
            return "/User/PayNew";
        }

        if(number == 0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
            if(AddressList.size() == 0)
                return "/User/PayNew";
            model.addAttribute("AddressList",AddressList);
            return "/User/PayNew";
        }


        Trade newTrade = new Trade();
        newTrade.setAddressId(String.valueOf(AddressList.get(Integer.parseInt(address) - 1).getAddressId()));
        newTrade.setFeedbackRemarks("");
        Date now = new Date();
        newTrade.setTradeCreateTime(now);
        newTrade.setTradeStatus(0);
        newTrade.setTradeTotalMoney(Double.parseDouble(Total));
        newTrade.setUserId(Long.parseLong(UserID));
        newTrade.setSellerId(Long.parseLong(ShopID));
        newTrade.setTradePayWay(PayWay);


        TradeDetail newTradeDetail = new TradeDetail();
        newTradeDetail.setTradeId(tradeService.save(newTrade).getTradeId());
        newTradeDetail.setProductAmount(Integer.parseInt(ProductAmount));
        newTradeDetail.setProductId(Integer.parseInt(ProductID));
        newTradeDetail.setProductTradePrice(Double.parseDouble(Total));

        tradeDetailService.save(newTradeDetail);

        int result =product.getProductStock() - Integer.parseInt(ProductAmount);
        product.setProductStock(result);
        productService.save(product);

        model.addAttribute("UserID", UserID);
        String IsFromShoppingCart = request.getParameter("IsFromShoppingCart");
        if(IsFromShoppingCart.equals("1")){
            List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
            shoppingCarts = shoppingCartService.findAllByUserId(Long.parseLong(UserID));
            ShoppingCart target = new ShoppingCart();
            for(int i = 0; i < shoppingCarts.size(); i++){
                if(shoppingCarts.get(i).getProductId() == Long.parseLong(ProductID)){
                    target = shoppingCarts.get(i);break;
                }
            }
            shoppingCartService.delete(target);
        }
        return "/User/SuccessNew";
    }

    @RequestMapping(value = "/Pay",params = "addAddress=addAddress")
    String addAddressBefore(HttpServletRequest request,Model model)
    {
        long UserID =Long.valueOf( request.getParameter("UserID"));
        boolean IsFromShoppingCart = Boolean.valueOf(request.getParameter("IsFromShoppingCart"));
        long ShopID = Long.valueOf(request.getParameter("ShopID"));
        long ProductID = Long.valueOf(request.getParameter("ProductID"));

        String SellerName = request.getParameter("SellerName");
        String ProductName = request.getParameter("ProductName");
        long ProductAmount = Long.valueOf(request.getParameter("ProductAmount"));
        double UnitPrice = Double.valueOf(request.getParameter("UnitPrice"));
        double TotalPay = Double.valueOf(request.getParameter("TotalPay"));

        model.addAttribute("UserID",UserID);
        model.addAttribute("IsFromShoppingCart",IsFromShoppingCart);
        model.addAttribute("ShopID",ShopID);
        model.addAttribute("ProductID",ProductID);
        model.addAttribute("SellerName",SellerName);
        model.addAttribute("ProductName",ProductName);
        model.addAttribute("ProductAmount",ProductAmount);
        model.addAttribute("UnitPrice",UnitPrice);
        model.addAttribute("TotalPay",TotalPay);

        return "/User/addAddressPage";
    }

    @RequestMapping("/Pay/addAddressAfter")
    String addAddressAfter(HttpServletRequest request,Model model)
    {
        long UserID =Long.valueOf( request.getParameter("UserID"));
        boolean IsFromShoppingCart = Boolean.valueOf(request.getParameter("IsFromShoppingCart"));
        long ShopID = Long.valueOf(request.getParameter("ShopID"));
        long ProductID = Long.valueOf(request.getParameter("ProductID"));

        String SellerName = request.getParameter("SellerName");
        String ProductName = request.getParameter("ProductName");
        long ProductAmount = Long.valueOf(request.getParameter("ProductAmount"));
        double UnitPrice = Double.valueOf(request.getParameter("UnitPrice"));
        double TotalPay = Double.valueOf(request.getParameter("TotalPay"));


        //add address
        String addressName = request.getParameter("addressName");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String detailAddress = request.getParameter("detailAddress");
        String district = request.getParameter("district");
        String tel = request.getParameter("tel");
        String postcode = request.getParameter("postcode");

        Address address = new Address();
        address.setAddressName(addressName);
        address.setProvince(province);
        address.setCity(city);
        address.setDetailAddress(detailAddress);
        address.setDistrict(district);
        address.setTel(tel);
        address.setPostcode(Integer.valueOf(postcode));
        address.setUserId(UserID);
        address.setIsDefaultAddress(false);
        addressService.save(address);


        model.addAttribute("UserID",UserID);
        model.addAttribute("IsFromShoppingCart",IsFromShoppingCart);
        model.addAttribute("ShopID",ShopID);
        model.addAttribute("ProductID",ProductID);
        model.addAttribute("SellerName",SellerName);
        model.addAttribute("ProductName",ProductName);
        model.addAttribute("ProductAmount",ProductAmount);
        model.addAttribute("UnitPrice",UnitPrice);
        model.addAttribute("TotalPay",TotalPay);
        model.addAttribute("AddressList",addressService.findAllByUserId(UserID));
        return "/User/PayNew";
    }
}
