package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.domain.Orderinfo;
import com.project_management.shoppingweb.domain.ProductInformation;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.User;

import javax.print.DocFlavor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

  public  class    SellerSQLFunction implements SellSQLInterface{

    private String sellerEmail;
    private String sellerPasswd;
    private String userEmail;
    private String userPasswd;
    private String AdminEmail;
    private String AdminPasswd;
    private long sellerID = 100001;
    private long userID = 200001;
    private long  AdminID = 30001;

    LinkedList<String> advtisementPaths = new LinkedList<String>();
    LinkedList<ProductInformation> products =new LinkedList<ProductInformation>();
    LinkedList<Orderinfo> orders = new LinkedList<Orderinfo>();

    private  Seller seller;
    private User user;

    public SellerSQLFunction()
    {
        Orderinfo f1 = new Orderinfo();
        Orderinfo f2 = new Orderinfo();
        Orderinfo f3 = new Orderinfo();
        Orderinfo u1 = new Orderinfo();
        Orderinfo u2 = new Orderinfo();
        Orderinfo u3 = new Orderinfo();

        f1.setOrderId(1111);
        f2.setOrderId(2222);
        f3.setOrderId(3333);
        u1.setOrderId(1001);
        u2.setOrderId(2002);
        u3.setOrderId(3003);

        f1.setOrderStatus(1);
        f2.setOrderStatus(1);
        f3.setOrderStatus(1);
        u1.setOrderStatus(2);
        u2.setOrderStatus(2);
        u3.setOrderStatus(2);

        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        f1.setOrderCreateTime(simFormat.parse("2008.1.12 12:22:01",null));
        f2.setOrderCreateTime(simFormat.parse("2012.1.12 12:22:01",null));
        f3.setOrderCreateTime(simFormat.parse("2011.1.12 12:22:01",null));
        u1.setOrderCreateTime(simFormat.parse("2002.1.12 12:22:01",null));
        u2.setOrderCreateTime(simFormat.parse("2014.1.12 12:22:01",null));
        u3.setOrderCreateTime(simFormat.parse("2012.1.12 12:22:01",null));

        f1.setOrderFinishTime(simFormat.parse("2012.12.12 12:22:01",null));
        f1.setOrderFinishTime(simFormat.parse("2017.12.1 12:22:01",null));
        f1.setOrderFinishTime(simFormat.parse("2017.11.12 12:22:01",null));

        f1.setSeller(getSellerInfromation(sellerID));
        f2.setSeller(getSellerInfromation(sellerID));
        f3.setSeller(getSellerInfromation(sellerID));
        u1.setSeller(getSellerInfromation(sellerID));
        u2.setSeller(getSellerInfromation(sellerID));
        u3.setSeller(getSellerInfromation(sellerID));


        orders.add(f1);
        orders.add(f2);
        orders.add(f3);
        orders.add(u1);
        orders.add(u2);
        orders.add(u3);


    }
    //注册接口
    public boolean  registerAccount(String email, String passwd, int mark)
    {
        switch (mark)
        {
            case 1:
                sellerEmail = email;
                sellerPasswd = passwd;
                seller = new Seller(sellerID);
                break;
            case 2:
                userEmail = email;
                userPasswd = passwd;
                User user = new User();
                break;
            case 3:
                AdminEmail = email;
                AdminPasswd = passwd;
                break;
            default:
                return false;
        }
        return true;
    }
    //mark：1-》商家 2-》用户  管理员是否需要注册方式，由设计人员考虑沟通
//登录接口
    public long loginByEmail(String email, String passwd)
    {
        if(email == null || passwd == null)
            return -1;
        if(email.equals(userEmail) && passwd.equals(userPasswd))
            return userID;
        if(email.equals(sellerEmail) && passwd.equals(sellerPasswd))
            return  sellerID;
        if(email.equals(AdminEmail) && email.equals(AdminPasswd))
            return AdminID;
        return  -2;
    }
     public long loginByUsername(String username, String passwd)
    {
        if(username == null || passwd == null)
            return  -1;
        if(username.equals(seller.getUsername()) && passwd.equals(seller.getPasswd()))
            return seller.getSellerId();
        if(username.equals(user.getUsername()) && passwd.equals(user.getPassword()))
            return user.getId();
        return -2;

    }
    //返回long为唯一的标识ID，因为小型项目，long就够了

    //获取商家信息接口
    public Seller getSellerInfromation(long sellerId)
    {
        if(sellerId == sellerID)
            return seller;
        return null;
    }
    //参数sellId为卖家的ID，唯一标识卖家的信息。返回JSON格式的字符串，信息以键值对存储
//验证密码
    public boolean checkPasswd(long sellerId, String passwd)
    {
        if(passwd != null && passwd.equals(getSellerInfromation(sellerId).getPasswd()))
            return  true;

        return false;

    }
    //在修改信息时候判断是否输入正确密码

    //写入新的修改信息
    public boolean writeInInformation(Seller tmpseller)
    {
        if(seller.getSellerId() == tmpseller.getSellerId())
        {
            seller = new Seller(tmpseller);
            return true;
        }
        return false;
    }
    //将对应信息打包为json字符串，然后发送给数据库
  /*商家JSON格式：
  {	(名称，类型)
	sellId	:	long，
	username:	String,
    email	:	String,
    passwd	:	String,
    phoneNum:	String,
    adress	:	String,
    sculpture:	String //(头像：图片的路径)
  }
  */

    //获得广告数据库
    public  LinkedList<String> getSellerAdvertisement(long sellerId)
    {
        if(sellerId == sellerID)
            return advtisementPaths;
        return  null;
    }
    //返回一连串的广告的图片路径

    //写入要发布的广告
    public  boolean writeInAdvertisment(long sellerId, LinkedList<String> paths)
    {
        if(sellerId == sellerID)
        {
            advtisementPaths = paths;
            return true;
        }
        return false;
    }

//逼哥再考虑一下广告收费的接口，这个我没考虑

    //创建商品
    public  boolean createProductInformation(long sellerId, ProductInformation productInfo)
    {
        if(sellerId == sellerID)
        {
            products.add(productInfo);
            return true;
        }
        return false;
    }
    //商品信息
  /*
  {
  	seller		:	long,
  	productId	:	long,
  	productName	:	String,
  	productNum	:	long,			//
  	productPrice:	long,			//产品价格，判断合法
  	JSONStr		:	String,			//产品其他属性
  	productPicture:	String,			//产品图片
  	productNote	:	String
  }
  */

    //获取商家所有商品
    public LinkedList<ProductInformation> getAllProducts(long sellerId)
    {
        if(sellerId == sellerID)
            return products;
        return null;
    }

    //删除某一个商品
    public boolean deleteProducts(long productID)
    {
        for(ProductInformation curProduct : products)
        {
            if(curProduct.getProductId() == productID)
            {
                products.remove(curProduct);
                return true;
            }
        }
        return false;
    }

    //为某个商品修改信息
    public boolean changedProducts(ProductInformation newProduct)
    {
        for(ProductInformation curProduct : products)
        {
            if(curProduct.getProductId() == newProduct.getProductId())
            {
                curProduct = newProduct;
                return true;
            }
        }
        return false;
    }

    //获取订单信息
    public LinkedList<Orderinfo> getTrade(long sellerId, int type)
    {
        if(sellerId != sellerID)
            return null;
        LinkedList<Orderinfo> retOrders = new LinkedList<Orderinfo>();
        for(Orderinfo curOrder : orders)
        {
            if(curOrder.getOrderStatus() == type)
                retOrders.add(curOrder);
        }
        return retOrders;
    }
    //type : 0 :所有的
    //1：完成的交易
    //2：所有的交易



}
