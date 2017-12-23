package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Address;
import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.TradeDetail;
import com.project_management.shoppingweb.service.AddressService;
import com.project_management.shoppingweb.service.User.User_ProductService;
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
import java.util.List;

@Controller
public class TradeDetailController {
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private User_TradeDetailService tradeDetailService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @Autowired
    private User_ProductService productService;

    @RequestMapping(value = "/TradeDetail", method = RequestMethod.GET)
    public String TradeDetail(HttpServletRequest request, Model model){
        String TradeID = request.getParameter("TradeID");
        System.out.println(TradeID);

        List<TradeDetail> TradeDetailList = new ArrayList<TradeDetail>();
        TradeDetailList = tradeDetailService.findByTradeId(Long.parseLong(TradeID));

        List<Trade> TradeList = new ArrayList<Trade>();
        TradeList = tradeService.findByTradeId(Long.parseLong(TradeID));

        if(TradeDetailList.size() == 0||TradeList.size() == 0){
            return "/User/TradeDetail";
        }

        List<TradeDetailToShow> tradeDetailToShows = new ArrayList<TradeDetailToShow>();
        for(int i = 0; i < TradeDetailList.size(); i++){
            TradeDetailToShow tradeDetailToShow = new TradeDetailToShow();
            tradeDetailToShow.tradeDetail = TradeDetailList.get(i);
            Product product = productService.findProductByProductID((long)TradeDetailList.get(i).getProductId());
            tradeDetailToShow.Name = product.getProductName();
            tradeDetailToShows.add(tradeDetailToShow);
        }
        model.addAttribute("TradeDetailList", tradeDetailToShows);

        String Status = "";
        if(TradeList.get(0).getTradeStatus() == 0){
            Status = "Processing";
        }
        if(TradeList.get(0).getTradeStatus() == 1){
            Status = "Preparing for shipment";
        }
        if(TradeList.get(0).getTradeStatus() == 2){
            Status = "Shipping";
        }
        if(TradeList.get(0).getTradeStatus() == 3){
            Status = "Finished";
        }

        model.addAttribute("Status", Status);

        List<Address> address = addressService.findAllByAddressId(Long.parseLong(TradeList.get(0).getAddressId()));
        String position = "";
        String Tel = "";
        String Postcode = "";
        String name = "";
        if(address.size() == 0){
            position = "The address has been deleted";
            model.addAttribute("position", position);
            model.addAttribute("Tel", Tel);
            model.addAttribute("Postcode", Postcode);
            model.addAttribute("name", name);
            return "/User/TradeDetail";
        }
        position = address.get(0).getProvince()+" "+address.get(0).getCity()+" "+address.get(0).getDistrict()+" "+address.get(0).getDetailAddress();
        Tel = address.get(0).getTel();
        Postcode = String.valueOf(address.get(0).getPostcode());
        name = userService.findByUserId(address.get(0).getUserId()).getUsername();
        model.addAttribute("position", position);
        model.addAttribute("Tel", Tel);
        model.addAttribute("Postcode", Postcode);
        model.addAttribute("name", name);

        System.out.println(position);
        System.out.println(Status);



        return "/User/TradeDetail";
    }
}

class TradeDetailToShow{
    public TradeDetail tradeDetail;
    public String Name;
}
