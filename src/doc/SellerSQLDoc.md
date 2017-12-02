```java
//注册接口
bool registerAccount(String email, String passwd, int mark)
	  //mark：1-》商家 2-》用户  管理员是否需要注册方式，由设计人员考虑沟通
//登录接口
long loginByEmail(String email, String passwd)
long loginByUsername(String username, String passwd)
  //返回long为唯一的标识ID，因为小型项目，long就够了
  
//获取商家信息接口
String getSellerInfromation(long sellerId)
  //参数sellId为卖家的ID，唯一标识卖家的信息。返回JSON格式的字符串，信息以键值对存储
//验证密码
bool checkPasswd(long sellerId, String passwd)
  //在修改信息时候判断是否输入正确密码

//写入新的修改信息
bool writeInInformation(Seller seller)
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
 List<String> getSellerAdvertisement(long sellerId)
  //返回一连串的广告的图片路径

//写入要发布的广告
  bool writeInAdvertisment(long sellerId, List<String> paths)

//逼哥再考虑一下广告收费的接口，这个我没考虑

//创建商品
  bool createProductInformation(long sellerId, ProductInformation productInfo)
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
List<ProductInformation> getAllProducts(long sellerId)

//删除某一个商品
bool deleteProducts(long productID)
  
//为某一个商品入库（增加数量）
bool addProducts(long productID, long num)

//为某一个商品出库（减少数量）
bool removeProducts(long productID, long num)
  
//获取订单信息
List<Orderinfo> getTrade(long sellerID, int type)
  //type : 0 :所有的
  		//1：完成的交易
  		//2：所有的交易
```

