package com.project_management.shoppingweb.service.Seller;





import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.Trade;

import java.util.LinkedList;

public interface SellSQLInterface {
    boolean registerAccount(String email, String passwd, int mark);
    //mark：1-》商家 2-》用户

    //登录接口
    int loginByEmail(String email, String passwd);
    int loginByUsername(String username, String passwd);
    //返回long为唯一的标识ID，因为小型项目，long就够了

    //获取商家信息接口
    Seller getSellerInformation(int sellerId);

    //验证密码
    boolean checkPasswd(int sellerId, String passwd);
    //在修改信息时候判断是否输入正确密码

    //写入新的修改信息
    boolean writeInInformation(Seller seller);
    //将对应信息打包为json字符串，然后发送给数据库


    //获得广告数据库
    LinkedList<String> getSellerAdvertisement(int sellerId);
    //返回一连串的广告的图片路径

    //写入要发布的广告
    boolean writeInAdvertisement(int sellerId, LinkedList<String> paths);

//逼哥再考虑一下广告收费的接口，这个我没考虑

    //创建商品
    boolean createProductInformation(int sellerId, Product productInfo);
    //商品信息

    //获取商家所有商品
    LinkedList<Product> getAllProducts(int sellerId);

    //删除某一个商品
    boolean deleteProducts(int productID);

    //为某个商品修改信息
    boolean changedProducts(Product newProduct);

    //获取订单信息
    LinkedList<Trade> getTrade(int sellerID, int type);
    //type : 0 :所有的
    //1：完成的交易
    //2：所有的交易


}
