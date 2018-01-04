package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RetractRemarkController {
    @Autowired
    private User_ProductService productService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/retractRemarks", method = RequestMethod.POST)

    public String RetractRemark(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ProductID = request.getParameter("ProductID");
        Product product = productService.findProductByProductID(Long.parseLong(ProductID));


            boolean isnull = false;
            if(product == null) isnull = true;
            model.addAttribute("productdetail", product);
            model.addAttribute("proisnull", isnull);
            long UserIDD = Long.parseLong(request.getParameter("UserID"));
            model.addAttribute("UserID", UserID);
            if(UserIDD == -1){
                model.addAttribute("UserName", "UserName");
            }
            else{
                User user = userService.findByUserId(UserIDD);
                model.addAttribute("UserName", user.getUsername());
            }
            return "/User/ProductDetail";

    }
}
