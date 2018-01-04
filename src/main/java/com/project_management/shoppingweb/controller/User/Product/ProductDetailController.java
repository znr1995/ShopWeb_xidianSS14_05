package com.project_management.shoppingweb.controller.User.Product;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductDetailController {
    @Autowired
    private User_ProductService userProductService;
    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public  String Detail(HttpServletRequest request, Model model){

        String ID = request.getParameter("productId");
        Product product = userProductService.findProductByProductID(Long.parseLong(ID));
        boolean isnull = false;
        if(product == null) isnull = true;
        model.addAttribute("productdetail", product);
        model.addAttribute("proisnull", isnull);
        long UserID = Long.parseLong(request.getParameter("UserID"));
        model.addAttribute("UserID", UserID);
        if(UserID == -1){
            model.addAttribute("UserName", "UserName");
        }
        else{
            User user = userService.findByUserId(UserID);
            model.addAttribute("UserName", user.getUsername());
        }
        return "/User/ProductDetail";
    }
}
