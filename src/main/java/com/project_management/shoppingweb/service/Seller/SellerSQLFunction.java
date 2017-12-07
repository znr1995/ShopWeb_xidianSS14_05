package com.project_management.shoppingweb.service.Seller;





import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.User;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

public  class    SellerSQLFunction implements SellSQLInterface{

  private String sellerEmail;
  private String sellerPasswd;
  private String userEmail;
  private String userPasswd;
  private String AdminEmail;
  private String AdminPasswd;
  private int sellerID = 10001;
  private int userID = 20001;
  private int  AdminID = 30001;

  LinkedList<String> advertisementPaths = new LinkedList<String>();
  LinkedList<Product> products =new LinkedList<Product>();
  LinkedList<Trade> trades = new LinkedList<Trade>();

  private Seller seller;
  private User user;


  private static SellerSQLFunction sellerSQLFunction = null;

  public static SellerSQLFunction getInstance()
  {
      if(sellerSQLFunction == null)
      {
          sellerSQLFunction = new SellerSQLFunction();
          return  sellerSQLFunction;
      }
      return sellerSQLFunction;
  }

  public SellerSQLFunction()
  {
      Trade f1 = new Trade();
      Trade f2 = new Trade();
      Trade f3 = new Trade();
      Trade u1 = new Trade();
      Trade u2 = new Trade();
      Trade u3 = new Trade();
      f1.setTradeId(1111);
      f2.setTradeId(2222);
      f3.setTradeId(3333);
      u1.setTradeId(1001);
      u2.setTradeId(2002);
      u3.setTradeId(3003);
      f1.setTradeStatus(1);
      f2.setTradeStatus(1);
      f3.setTradeStatus(1);
      u1.setTradeStatus(2);
      u2.setTradeStatus(2);
      u3.setTradeStatus(2);

      SimpleDateFormat simFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
      try {
          f1.setTradeCreateTime(simFormat.parse("2008.1.12 12:22:01"));
          f2.setTradeCreateTime(simFormat.parse("2012.1.12 12:22:01"));
          f3.setTradeCreateTime(simFormat.parse("2011.1.12 12:22:01"));
          u1.setTradeCreateTime(simFormat.parse("2002.1.12 12:22:01"));
          u2.setTradeCreateTime(simFormat.parse("2014.1.12 12:22:01"));
          u3.setTradeCreateTime(simFormat.parse("2012.1.12 12:22:01"));
          f1.setTradeCreateTime(simFormat.parse("2012.12.12 12:22:01"));
          f2.setTradeCreateTime(simFormat.parse("2017.12.1 12:22:01"));
          f3.setTradeCreateTime(simFormat.parse("2017.11.12 12:22:01"));
      }
      catch (Exception e)
      {
          System.out.println("!!!!!!SellerSQLFunctionError!!!!!!");
      }

      f1.setSellerId(sellerID);
      f2.setSellerId(sellerID);
      f3.setSellerId(sellerID);
      u1.setSellerId(sellerID);
      u2.setSellerId(sellerID);
      u3.setSellerId(sellerID);
      trades.add(f1);
      trades.add(f2);
      trades.add(f3);
      trades.add(u1);
      trades.add(u2);
      trades.add(u3);




      Product p1 = new Product();
      Product p2 = new Product();
      p1.setProductName("Java InputBook");
      p1.setBrandName("book");
      p1.setIsOnSale(true);
      p1.setProductId(101);
      p1.setProductBriefInfo("{auther:znr, publictime:2014}");
      p1.setProductMarketPrice(20.01);
      p1.setProductStock(100);
      p1.setSellerId(sellerID);

      p2.setProductName("2IN1 NOTEBOOK");
      p2.setBrandName("computer");
      p2.setIsOnSale(true);
      p2.setProductId(111);
      p2.setProductBriefInfo("{hello:ASUS, publictime:2017}");
      p2.setProductMarketPrice(5000);
      p2.setProductStock(20);
      p2.setSellerId(sellerID);

      products.add(p1);
      products.add(p2);

      advertisementPaths.add("advertisementPaths1");
      advertisementPaths.add("advertisementPaths2");
      advertisementPaths.add("advertisementPaths3");

      seller = new Seller();

      seller.setUsername("seller");
      seller.setPassword("passwd");
      seller.setSculpture("hello.png");
      seller.setPhoneNum("110");
      seller.setEmail("xidian@edu.cn");
      seller.setAddress("xidian_xian_china");
      seller.setSellerId(sellerID);

  }

  //注册接口
  public boolean  registerAccount(String email, String passwd, int mark)
  {
      switch (mark)
      {
          case 1:
              sellerEmail = email;
              sellerPasswd = passwd;
              seller = new Seller();
              seller.setSellerId(sellerID);
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
  public int loginByEmail(String email, String passwd)
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
   public int loginByUsername(String username, String passwd)
  {
      if(username == null || passwd == null)
          return  -1;
      if(username.equals(seller.getUsername()) && passwd.equals(seller.getPassword()))
          return seller.getSellerId();
      if(username.equals(user.getUsername()) && passwd.equals(user.getPassword()))
          return 1001;
      return -2;

  }



  //获取商家信息接口
  public Seller getSellerInformation(int sellerId)
  {
      if(sellerId == sellerID)
          return seller;
      return null;
  }

    //验证密码
  public boolean checkPasswd(int sellerId, String passwd)
  {
      if(passwd != null && passwd.equals(getSellerInformation(sellerId).getPassword()))
          return  true;

      return false;

  }
  //在修改信息时候判断是否输入正确密码

  //写入新的修改信息
  public boolean writeInInformation(Seller tmpseller)
  {
      if(seller.getSellerId() == tmpseller.getSellerId())
      {
          seller.setAddress(tmpseller.getAddress());
          seller.setEmail(tmpseller.getEmail());
          seller.setPhoneNum(tmpseller.getPhoneNum());
          seller.setSculpture(tmpseller.getSculpture());
          seller.setUsername(tmpseller.getUsername());
          seller.setPassword(tmpseller.getPassword());
          return true;
      }
      return false;
  }


  //获得广告数据库
  public  LinkedList<String> getSellerAdvertisement(int sellerId)
  {
      if(sellerId == sellerID)
          return advertisementPaths;
      return  null;
  }
  //返回一连串的广告的图片路径

  //写入要发布的广告
  public  boolean writeInAdvertisement(int sellerId, LinkedList<String> paths)
  {
      if(sellerId == sellerID)
      {
          advertisementPaths = paths;
          return true;
      }
      return false;
  }


  //创建商品
  public  boolean createProductInformation(int sellerId, Product product)
  {
      if(sellerId == sellerID)
      {
          products.add(product);
          return true;
      }
      return false;
  }

  //获取商家所有商品
  public LinkedList<Product> getAllProducts(int sellerId)
  {
      if(sellerId == sellerID)
          return products;
      return null;
  }

  //删除某一个商品
  public boolean deleteProducts(int productID)
  {
      for(Product curProduct : products)
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
  public boolean changedProducts(Product newProduct)
  {
      for(Product curProduct : products)
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
  public LinkedList<Trade> getTrade(int sellerId, int type)
  {
      if(sellerId != sellerID)
          return null;
      LinkedList<Trade> retOrders = new LinkedList<Trade>();
      for(Trade curOrder : trades)
      {
          if(curOrder.getTradeStatus() == type)
              retOrders.add(curOrder);
      }
      return retOrders;
  }




}
