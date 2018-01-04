package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartPortalController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private User_ProductService userProductService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/CartPortal", method = RequestMethod.POST)
    public String CartPortal(HttpServletRequest request, Model model){
        List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
        shoppingCarts = shoppingCartService.findAllByShoppingcartId(Long.parseLong(request.getParameter("ShoppingCartID")));
            String ID = String.valueOf(shoppingCarts.get(0).getProductId());
            Product product = userProductService.findProductByProductID(Long.parseLong(ID));
            if(product == null){
               Long UserID = Long.parseLong(request.getParameter("UserID"));
                model.addAttribute("UserID", UserID);
                if(UserID == -1){
                    model.addAttribute("UserName", "UserName");
                }
                else{
                    User user = userService.findByUserId(UserID);
                    model.addAttribute("UserName", user.getUsername());
                }
                return "/User/productoops";
            }



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
