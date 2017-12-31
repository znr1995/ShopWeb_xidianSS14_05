package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Collection;
import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductCollection;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductCollectionService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sightmaple on 2017/12/29.
 */
@Controller
public class DeleteFavoriteController {
    @Autowired
    private User_ProductCollectionService user_productCollectionService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/deletefavorite",method = RequestMethod.GET)
    public String deleteFavorite(HttpServletRequest servletRequest,Model model){

        String User_ID =  servletRequest.getParameter("UserID");

        String Product_ID = servletRequest.getParameter("ProductID");


        List<ProductCollection> collectionList = new ArrayList<>();

        collectionList = user_productCollectionService.findAllByUserId(Long.parseLong(User_ID));

        User user = userService.findByUserId(Long.parseLong(User_ID));


        ProductCollection productCollection = new ProductCollection();

        if (collectionList.size()!=0){
            for (int i = 0;i<collectionList.size();i++){
                if (Long.parseLong(Product_ID)==collectionList.get(i).getProductId()){
                    productCollection = collectionList.get(i);
                    break;
                }
            }
        }

        user_productCollectionService.delete(productCollection);

        List<ProductCollection> productCollections = user_productCollectionService.findAllByUserId(Long.parseLong(User_ID));




        if (productCollections.size()==0){
            model.addAttribute("UserID",User_ID);
            model.addAttribute("UserName",user.getUsername());
            System.out.println("if ok");
            return "User/FavoriteNew";
        }
        List<ProductCollection> productCollectionList = user_productCollectionService.findAllByUserId(Long.parseLong(User_ID));


        List<FavoriteToShow> FavoriteProduct = new ArrayList<FavoriteToShow>();

        for (int i = 0; i<productCollectionList.size();i++){
            FavoriteToShow favoriteToShow = new FavoriteToShow();
            favoriteToShow.ID = String.valueOf(productCollectionList.get(i).getProductId());
            Product product = productService.findProductByProductID(productCollectionList.get(i).getProductId());
            favoriteToShow.Name = product.getProductName();
            FavoriteProduct.add(favoriteToShow);
        }
        model.addAttribute("UserName",user.getUsername());
        model.addAttribute("ProductList", FavoriteProduct);
        model.addAttribute("UserID", User_ID);
        return "/User/FavoriteNew";
    }
}
