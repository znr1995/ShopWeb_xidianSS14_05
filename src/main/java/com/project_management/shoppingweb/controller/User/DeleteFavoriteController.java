package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.*;
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
public class DeleteFavoriteController {
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

    @RequestMapping(value = "/deletefavorite", method = RequestMethod.POST)
    public String DeleteFavorite(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ID = request.getParameter("ID");
        User user = userService.findByUserId(Long.parseLong(UserID));

        model.addAttribute("UserID", UserID);
        model.addAttribute("UserName", user.getUsername());

        List<ProductCollection> productCollections = new ArrayList<ProductCollection>();
        productCollections = productCollectionService.findAllByCollectionId(Long.parseLong(ID));
        if(productCollections.size() != 0){
            productCollectionService.delete(productCollections.get(0));
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
                Product product = productService.findProductByProductID(FavoritelistP.get(i).getProductId());
                if(product == null){
                    favoriteToShow.SID = "-1";
                    favoriteToShow.NameP = "undercarriage";
                    favoriteToShow.NameS = "Missed";
                    favoriteToShow.Photo = "233";
                    FavoriteProduct.add(favoriteToShow);
                    continue;
                }
                favoriteToShow.SID = String.valueOf(product.getSellerId());
                favoriteToShow.NameP = product.getProductName();
                favoriteToShow.NameS = sellerService.findBySellerId(Long.parseLong(favoriteToShow.SID)).getShopname();
                favoriteToShow.Photo = product.getProductPhoto();
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
                Seller seller = sellerService.findBySellerId(FavoriteShop.get(i).getSellerId());
                if(seller == null){
                    favoriteToShow.NameS = "Missed";
                    favoriteToShow.Photo = "233";
                    continue;
                }
                favoriteToShow.NameS = sellerService.findBySellerId(FavoriteShop.get(i).getSellerId()).getShopname();
                favoriteToShow.Photo = sellerService.findBySellerId(FavoriteShop.get(i).getSellerId()).getSculpture();
                FavoriteShops.add(favoriteToShow);
            }
            model.addAttribute("ShopList", FavoriteShops);
        }

        return "/User/FavoriteNew";
    }
}
