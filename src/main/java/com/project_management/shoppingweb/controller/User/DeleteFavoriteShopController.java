package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductCollection;
import com.project_management.shoppingweb.domain.ShopCollection;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.SellerService;
import com.project_management.shoppingweb.service.User.User_ProductCollectionService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShopCollectionService;
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
public class DeleteFavoriteShopController {
    @Autowired
    private User_ProductCollectionService productCollectionService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private User_ShopCollectionService shopCollectionService;
    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/deletefavoriteShop", method = RequestMethod.POST)
    public String DeleteFavoriteShop(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ID = request.getParameter("ID");
        User user = userService.findByUserId(Long.parseLong(UserID));

        model.addAttribute("UserID", UserID);
        model.addAttribute("UserName", user.getUsername());

        List<ShopCollection> shopCollections = new ArrayList<ShopCollection>();
        shopCollections = shopCollectionService.findAllById(Long.parseLong(ID));
        if(shopCollections.size() != 0){
            shopCollectionService.delete(shopCollections.get(0));
        }

        List<ProductCollection> FavoritelistP = new ArrayList<ProductCollection>();
        FavoritelistP = productCollectionService.findAllByUserId(Long.parseLong(UserID));
        List<ShopCollection> FavoriteShop = new ArrayList<ShopCollection>();
        FavoriteShop = shopCollectionService.findAllByUserId(Long.parseLong(UserID));

        if(FavoritelistP.size() != 0){
            List<FavoriteToShow> FavoriteProduct = new ArrayList<FavoriteToShow>();
            for(int i = 0; i < FavoritelistP.size(); i++){
                FavoriteToShow favoriteToShow = new FavoriteToShow();
                favoriteToShow.ID = String.valueOf(FavoritelistP.get(i).getCollectionId());
                favoriteToShow.PID = String.valueOf(FavoritelistP.get(i).getProductId());
                favoriteToShow.SID = String.valueOf(productService.findProductByProductID(FavoritelistP.get(i).getProductId()).getSellerId());
                Product product = productService.findProductByProductID(FavoritelistP.get(i).getProductId());
                favoriteToShow.NameP = product.getProductName();
                favoriteToShow.NameS = sellerService.findBySellerId(Long.parseLong(favoriteToShow.SID)).getShopname();
                FavoriteProduct.add(favoriteToShow);
            }
            model.addAttribute("ProductList", FavoriteProduct);
        }




        //------------------------------------------------------------------------------------------------------------------------------------------------


        if(FavoriteShop.size() != 0){
            List<FavoriteToShow> FavoriteShops = new ArrayList<FavoriteToShow>();
            for(int i = 0; i < FavoriteShop.size(); i++){
                FavoriteToShow favoriteToShow = new FavoriteToShow();
                favoriteToShow.ID = String.valueOf(FavoriteShop.get(i).getId());
                favoriteToShow.SID = String.valueOf(FavoriteShop.get(i).getSellerId());
                favoriteToShow.NameS = sellerService.findBySellerId(FavoriteShop.get(i).getSellerId()).getShopname();
                FavoriteShops.add(favoriteToShow);
            }
            model.addAttribute("ShopList", FavoriteShops);
        }

        return "/User/FavoriteNew";

    }
}