package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ShoppingCart;
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
public class ModifySCAmountController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private User_ProductService productService;

    @RequestMapping(value = "/ModifyAmount", method = RequestMethod.POST)

    public String ModifySCAmount(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShoppingCartID = request.getParameter("ShoppingCartID");
        String Amount = request.getParameter("Amount");
        model.addAttribute("UserName", userService.findByUserId(Long.parseLong(UserID)).getUsername());
        model.addAttribute("UserID", UserID);

        List<ShoppingCart> ShoppingCartList = new ArrayList<ShoppingCart>();
        ShoppingCartList = shoppingCartService.findAllByShoppingcartId(Long.parseLong(ShoppingCartID));

        if(ShoppingCartList.size() != 0){
            ShoppingCartList.get(0).setProductAmount(Integer.parseInt(Amount));
            shoppingCartService.save(ShoppingCartList.get(0));
        }


        List<ShoppingCart> GlobalShoppingCart = new ArrayList<ShoppingCart>();
        GlobalShoppingCart = shoppingCartService.findAllByUserId(Long.parseLong(UserID));


        if(GlobalShoppingCart.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/ShoppingCartNew";
        }

        List<ShoppingCartToShow> shoppingCartToShowList = new ArrayList<ShoppingCartToShow>();
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            ShoppingCartToShow shoppingCartToShow = new ShoppingCartToShow();
            shoppingCartToShow.shoppingCart = GlobalShoppingCart.get(i);
            Product product = productService.findProductByProductID(GlobalShoppingCart.get(i).getProductId());
            if(product == null){
                shoppingCartToShow.Name = "undercarriage";
                shoppingCartToShow.Photo = "233";
                shoppingCartToShow.UPrice = "/";
                shoppingCartToShow.TPrice = "/";
                shoppingCartToShow.productStock = "0";
                shoppingCartToShowList.add(shoppingCartToShow);
                continue;
            }
            shoppingCartToShow.Name = product.getProductName();
            shoppingCartToShow.Photo = product.getProductPhoto();
            shoppingCartToShow.UPrice = String.valueOf(product.getProductPrice());
            shoppingCartToShow.TPrice = String.valueOf(product.getProductPrice() * shoppingCartToShow.shoppingCart.getProductAmount());
            shoppingCartToShow.productStock = String.valueOf(product.getProductStock());
            shoppingCartToShowList.add(shoppingCartToShow);
        }

        model.addAttribute("UserID", UserID);
        model.addAttribute("GlobalShoppingCart", shoppingCartToShowList);

        return "/User/ShoppingCartNew";
    }
}
