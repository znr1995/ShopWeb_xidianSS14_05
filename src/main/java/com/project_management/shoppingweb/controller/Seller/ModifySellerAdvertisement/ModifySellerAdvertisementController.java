package com.project_management.shoppingweb.controller.Seller.ModifySellerAdvertisement;



import com.project_management.shoppingweb.domain.Income;
import com.project_management.shoppingweb.domain.ProductAdvertisement;
import com.project_management.shoppingweb.domain.SellerAdvertisement;
import com.project_management.shoppingweb.service.Seller.Seller_CopyFile;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.awt.image.ImageWatched;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/Seller/ModifySellerAdvertisement")
public class ModifySellerAdvertisementController {

    @Autowired
    private Seller_SellerService sellerSellerService;

    private long sellerID = -1;
    private int notPassStatus = 0;
    private int passStatus = 1;
    private int notPayStatus = 2;

    //界面显示的类
    class ProductAdvertisementPlus {

        public String getStatusString() {
            return statusString;
        }

        public void setStatusString(String statusString) {
            this.statusString = statusString;
        }

        public String getTypeString() {
            return typeString;
        }

        public void setTypeString(String typeString) {
            this.typeString = typeString;
        }

        private String statusString;
        private String typeString;
        private String description;
        private String startDate;
        private String endDate;
        private long advertisementId;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public long getAdvertisementId() {
            return advertisementId;
        }

        public void setAdvertisementId(long advertisementId) {
            this.advertisementId = advertisementId;
        }
    }

    class SellerAdvertisementPlus {
        public String getStatusString() {
            return statusString;
        }

        public void setStatusString(String statusString) {
            this.statusString = statusString;
        }

        private String statusString;
        private String description;
        private String startDate;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public long getAdvertisementId() {
            return advertisementId;
        }

        public void setAdvertisementId(long advertisementId) {
            this.advertisementId = advertisementId;
        }

        private String endDate;
        private long advertisementId;

    }

    //从其他界面跳转到广告主界面的接口
    @RequestMapping("ModifySellerAdvertisementHandler")
    public String jumpToModifySellerAdvertisementMainPage(@ModelAttribute("SellerID")long sellerId, Model model)
    {
        sellerID = sellerId;
        List<ProductAdvertisement> productAdvertisements = sellerSellerService.getProductAdvertisementBySellerID(sellerID);
        List<SellerAdvertisement> sellerAdvertisements = sellerSellerService.getSellerAdvertisements(sellerID);
        LinkedList<ProductAdvertisementPlus> productAdvertisementPluses = new LinkedList<ProductAdvertisementPlus>();
        LinkedList<SellerAdvertisementPlus> sellerAdvertisementPluses = new LinkedList<SellerAdvertisementPlus>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for(ProductAdvertisement productAdvertisement : productAdvertisements)
        {
            String startDate = "NULL",endDate = "NULL";
            ProductAdvertisementPlus productAdvertisementPlus = new ProductAdvertisementPlus();
            productAdvertisementPlus.setAdvertisementId(productAdvertisement.getAdvertisementId());
            productAdvertisementPlus.setDescription(productAdvertisement.getDescription());
            try {
                startDate=sdf.format(productAdvertisement.getStartDate());
            }catch (Exception e) { }
            productAdvertisementPlus.setStartDate(startDate);
            try {
                endDate=sdf.format(productAdvertisement.getEndDate());
            }catch (Exception e) { }
            productAdvertisementPlus.setEndDate(endDate);
            productAdvertisementPlus.setStatusString(getStatusString(productAdvertisement.getStatus()));
            productAdvertisementPlus.setTypeString(getTypeString(productAdvertisement.getType()));
            productAdvertisementPluses.add(productAdvertisementPlus);
        }
        for(SellerAdvertisement productAdvertisement : sellerAdvertisements)
        {
            String startDate = "NULL",endDate = "NULL";
            SellerAdvertisementPlus productAdvertisementPlus = new SellerAdvertisementPlus();
            productAdvertisementPlus.setAdvertisementId(productAdvertisement.getAdvertisementId());
            productAdvertisementPlus.setDescription(productAdvertisement.getDescription());
            try {
                startDate=sdf.format(productAdvertisement.getStartDate());
            }catch (Exception e) { }
            productAdvertisementPlus.setStartDate(startDate);
            try {
                endDate=sdf.format(productAdvertisement.getEndDate());
            }catch (Exception e) { }
            productAdvertisementPlus.setEndDate(endDate);
            productAdvertisementPlus.setStatusString(getStatusString(productAdvertisement.getStatus()));
            sellerAdvertisementPluses.add(productAdvertisementPlus);
        }

        model.addAttribute("SellerID",sellerID);
        model.addAttribute("productAdvertisements",productAdvertisementPluses);
        model.addAttribute("sellerAdvertisements",sellerAdvertisementPluses);
        return "/Seller/ModifySellerAdverMainPage";
    }

    //主界面的returnBack
    @RequestMapping("ReturnBack")
    public String returnToMainPage(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }

    //修改/添加广告ReturnBack
    @RequestMapping("ReturnBack1")
    public String returnToPage1(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    //添加广告前的controller处理
    @RequestMapping("AddNewAdvertisement")
    public String jumpToProductAdvertisementPageFormAddNewAdvertisement(Model model)
    {
        model.addAttribute("AddOrModify","Add");
        model.addAttribute("Products",sellerSellerService.getSellerProducts(sellerID));
        model.addAttribute("SellerID",sellerID);
        return "/Seller/AddProductAdvertisementPage";
    }

    //修改产品广告前controller处理
    @RequestMapping("ModifyProductAdvertisement")
    public String jumpToProductAdvertisementPage(HttpServletRequest request, Model model, RedirectAttributes attributes)
    {
        long advertisementId = Long.valueOf(request.getParameter("modify"));
        ProductAdvertisement productAdvertisement = sellerSellerService.getProductAdvertisementByProductAdvertisementId(advertisementId);
        model.addAttribute("AddOrModify","Modify");
        model.addAttribute("SellerID",sellerID);
        model.addAttribute("status",
                productAdvertisement.getStatus() == 1 ? "not passed yet!" : "passed,on display!");
        model.addAttribute("type",
                productAdvertisement.getType() == 1? "roll advertisement": "list advertisement");
        model.addAttribute("ProductName",sellerSellerService.getProduct(productAdvertisement.getProductId()).getProductName());
        model.addAttribute("ProductAdvertisement",productAdvertisement.getAdvertisementId());
        model.addAttribute("ProductID",productAdvertisement.getProductId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            model.addAttribute("startDate",sdf.format(productAdvertisement.getStartDate()));
            model.addAttribute("endDate",sdf.format(productAdvertisement.getEndDate()));
        }catch (Exception e)
        {
            attributes.addAttribute("errorMessage","trade's date wrong !" + e.getMessage());
            return "redirect:/error/errorHandler";
        }

        model.addAttribute("pictureUrl",productAdvertisement.getPictureUrl());
        model.addAttribute("description",productAdvertisement.getDescription());
        model.addAttribute("IsProductAdvertisement","true");
        model.addAttribute("advertisementType","Product Advertisement");
        model.addAttribute("SellerAdvertisement",-1);
        return "/Seller/ModifyProductAdvertisementPage";
    }

    //修改商家广告前controller处理
    @RequestMapping("ModifySellerAdvertisement")
    public String jumpToSellerAdvertisement(HttpServletRequest request, Model model,
                                            RedirectAttributes attributes)
    {
        long advertisementId = Long.valueOf(request.getParameter("modify"));
        SellerAdvertisement sellerAdvertisement = sellerSellerService.getSellerAdvertisement(advertisementId);
        model.addAttribute("AddOrModify","Modify");
        model.addAttribute("SellerID",sellerID);
        model.addAttribute("status",
                sellerAdvertisement.getStatus() == 1 ? "not passed yet!" : "passed,on display!");
      //  model.addAttribute("SellerAdvertisement",advertisementId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            model.addAttribute("startDate",sdf.format(sellerAdvertisement.getStartDate()));
            model.addAttribute("endDate",sdf.format(sellerAdvertisement.getEndDate()));
        }catch (Exception e)
        {
            attributes.addAttribute("errorMessage",e.getMessage());
            return "redirect:/error/errorHandler";
        }
        model.addAttribute("SellerAdvertisement",sellerAdvertisement.getAdvertisementId());
        model.addAttribute("pictureUrl",sellerAdvertisement.getPictureUrl());
        model.addAttribute("description",sellerAdvertisement.getDescription());
        model.addAttribute("IsProductAdvertisement","false");
        model.addAttribute("advertisementType","Seller Advertisement");
        return "/Seller/ModifyProductAdvertisementPage";
    }

    //修改或添加广告后的处理
    @RequestMapping(value = "ModifyAdvertisement",method = RequestMethod.POST)
    public String advertisementHandler(@RequestParam(value = "pictureUrl",required = true)MultipartFile file,
                                       HttpServletRequest request,
                                       Model model,
                                       RedirectAttributes attributes)
    {
        int option = -1;
        try {
            String str = request.getParameter("advertisementType");
            option = Integer.valueOf(request.getParameter("advertisementType"));
        }catch (Exception e)
        {
            String str = request.getParameter("IsProductAdvertisement");
            option  = (request.getParameter("IsProductAdvertisement")).equals("true") ? 1 : 2;
        }

        Date start = new Date();Date end= new Date();
        String pictureUrl = null;

        String addOrModify = request.getParameter("AddOrModify");
        if(addOrModify.equals("Add"))
        {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                start = sdf.parse(startDate);
                end = sdf.parse(endDate);
            }
            catch (Exception e)
            {
                attributes.addAttribute("errorMessage","date not allow empty!" + "errorMessage :" +  e.getMessage());
                return "redirect:/error/errorHandler";
            }

            if(file.isEmpty())
            {
                attributes.addAttribute("errorMessage","picture do not allow null when add advertisement !");
                return "redirect:/error/errorHandler";
            }

            try {
                pictureUrl = Seller_CopyFile.getInstance().copyFile(file);
            }
            catch (Exception e)
            {
                if(file.getSize() > 0)
                {
                    attributes.addAttribute("errorMessage","picture save error!");
                    return "redirect:/error/errorHandler";
                }else {
                    attributes.addAttribute("errorMessage","picture do not allow null!");
                    return "redirect:/error/errorHandler";
                }
            }

        }
        else {
            try {

                if(file.getSize() > 0)
                    pictureUrl = Seller_CopyFile.getInstance().copyFile(file);
            }
            catch (Exception e)
            {
                if(file != null)
                {
                    attributes.addAttribute("errorMessage","picture save error!");
                    return "redirect:/error/errorHandler";
                }
                //file 可以为null
            }
        }


        if(1 == option)
        {
            return modifyProductAdvertisement(pictureUrl,start,end,request,model,attributes);
            //product advertisement
        }
        else
        {
            return modifySellerAdvertisement(pictureUrl,start,end,request,model, attributes);
            //shopadvertisement
        }

    }

    //增加或修改商品广告信息
    public String modifyProductAdvertisement(String pictureUrl,Date startDate, Date endDate,HttpServletRequest request,Model model, RedirectAttributes attributes)
    {
        model.addAttribute("SellerID",sellerID);
        attributes.addAttribute("SellerID",sellerID);
        //TODO:全部每一个修改选项有效,不能为空
        ProductAdvertisement productAdvertisement = null;
        String addOrModify = request.getParameter("AddOrModify");
        if(addOrModify.equals("Add"))
        {
            //新建商品广告
            productAdvertisement = new ProductAdvertisement();

            //判断起止日期是否合法
            if(startDate.after(endDate))
            {
                attributes.addAttribute("errorMessage","endDate <= startDate ,not allow!!");
                return "redirect:/error/errorHandler";
            }

            //计算广告投放时间
            int dayNum = getDateDifference(startDate, endDate);

            productAdvertisement.setStatus(notPayStatus);  // 1 - 未判断， 0 - 通过 2 - 未付款
            long productId = Long.valueOf(request.getParameter("productIDs"));
            int locationId = Integer.valueOf(request.getParameter("type"));  // 1-滚动，2-列表广告
            //判断数据库是否有price相关的数据
            if(!sellerSellerService.hasCorrentPrice()) {
                attributes.addAttribute("errorMessage","not find price file,the admin is too lazy!");
                return "redirect:/error/errorHandler";
            }
            productAdvertisement.setPrice(dayNum *(locationId == 1 ? sellerSellerService.getProductRollAdvertisementPrice() :sellerSellerService.getSellerListAdvertisementPrice()));
            productAdvertisement.setProductId(productId);
            productAdvertisement.setType(locationId);
            productAdvertisement.setStartDate(startDate);
            productAdvertisement.setEndDate(endDate);
            productAdvertisement.setSellerName(sellerSellerService.getSellerById(sellerID).getUsername());

            //添加income
            Income income = new Income();
            income.setCommission(sellerSellerService.getProductRollAdvertisementPrice());
            income.setDate(new Date());
            income.setSellerId(sellerID);
            income.setSellerName(sellerSellerService.getSellerById(sellerID).getUsername());
            income.setType("advertisement");
            sellerSellerService.writeInIncome(income);
        }
        else
        {
            //修改广告
            long advertisementId = Long.valueOf(request.getParameter("ProductAdvertisement"));
            productAdvertisement = sellerSellerService.getProductAdvertisementByProductAdvertisementId(advertisementId);
        }

        // 修改广告只允许修改描述和图片
        if(pictureUrl != null)
            productAdvertisement.setPictureUrl(pictureUrl);
        //广告描述非空
        if(request.getParameter("description").isEmpty()){
               attributes.addAttribute("errorMessage","The description can't be null!");
               return "redirect:/error/errorHandler";
            }
        productAdvertisement.setDescription(request.getParameter("description"));




        sellerSellerService.writeInProductAdvertisement(productAdvertisement);


        if(productAdvertisement.getStatus() == 2)
        {
            //跳到支付页面

            model.addAttribute("type","ProductAdvertisement");
            model.addAttribute("minPrice",productAdvertisement.getPrice());
            model.addAttribute("AdvertisementId",productAdvertisement.getAdvertisementId());
            return "/Seller/pay";
        }
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }


    //增加或修改商家广告信息
    public String modifySellerAdvertisement(String pictureUrl,Date startDate, Date endDate,HttpServletRequest request,Model model, RedirectAttributes attributes)
    {
        model.addAttribute("SellerID",sellerID);
        attributes.addAttribute("SellerID",sellerID);
        //TODO:全部每一个修改选项有效,不能为空
        SellerAdvertisement sellerAdvertisement = null;
        String addOrModify = request.getParameter("AddOrModify");
        if(addOrModify.equals("Add"))
        {
            //新建商家广告
            sellerAdvertisement = new SellerAdvertisement();


            //判断起止日期是否合法
            if(startDate.after(endDate))
            {
                attributes.addAttribute("errorMessage","endDate <= startDate ,not allow!!");
                return "redirect:/error/errorHandler";
            }

            //计算广告投放时间
            int dayNum = getDateDifference(startDate, endDate);


            //判断数据库是否有price相关的数据
            if(!sellerSellerService.hasCorrentPrice()) {
                attributes.addAttribute("errorMessage","not find price file,the admin is too lazy!");
                return "redirect:/error/errorHandler";
            }


            sellerAdvertisement.setPrice(dayNum * sellerSellerService.getSellerListAdvertisementPrice());
            sellerAdvertisement.setStartDate(startDate);
            sellerAdvertisement.setEndDate(endDate);
            sellerAdvertisement.setSellerName(sellerSellerService.getSellerById(sellerID).getUsername());
            sellerAdvertisement.setSellerId(sellerID);
            sellerAdvertisement.setStatus(notPayStatus);

            //添加income
            Income income = new Income();
            income.setCommission(sellerSellerService.getSellerListAdvertisementPrice());
            income.setDate(new Date());
            income.setSellerId(sellerID);
            income.setSellerName(sellerSellerService.getSellerById(sellerID).getUsername());
            income.setType("advertisement");
            sellerSellerService.writeInIncome(income);

        }
        else
        {
            //修改广告
            long advertisementId = Long.valueOf(request.getParameter("SellerAdvertisement"));
            sellerAdvertisement = sellerSellerService.getSellerAdvertisement(advertisementId);
        }

        // 修改广告只允许修改描述和图片
        if(pictureUrl != null)
            sellerAdvertisement.setPictureUrl(pictureUrl);
        //广告描述非空
        if(request.getParameter("description").isEmpty()){
            attributes.addAttribute("errorMessage","The description can't be null!");
            return "redirect:/error/errorHandler";
        }
        sellerAdvertisement.setDescription(request.getParameter("description"));


        sellerSellerService.writeInSellerAdvertisement(sellerAdvertisement);

        if(sellerAdvertisement.getStatus() == 2)
        {
            //未付款
            model.addAttribute("type","SellerAdvertisement");
            model.addAttribute("minPrice",sellerAdvertisement.getPrice());
            model.addAttribute("AdvertisementId",sellerAdvertisement.getAdvertisementId());
            return "/Seller/pay";
        }

        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    @RequestMapping("pay")
    public String payFinishHandler(HttpServletRequest request,Model model,RedirectAttributes attributes)
    {
        long advertisementId = Long.valueOf(request.getParameter("AdvertisementId"));
        String type = request.getParameter("type");
        if("success".equals(request.getParameter("payStatue")))
        {

            double payPrice = Double.valueOf(request.getParameter("payPrice"));
            //支付成功
            if(type.equals("ProductAdvertisement"))
            {
              ProductAdvertisement productAdvertisement =  sellerSellerService.getProductAdvertisementByProductAdvertisementId(advertisementId);
                productAdvertisement.setStatus(notPassStatus);
                productAdvertisement.setPrice(payPrice);
                sellerSellerService.writeInProductAdvertisement(productAdvertisement);
            }
            else
            {
                SellerAdvertisement productAdvertisement =  sellerSellerService.getSellerAdvertisement(advertisementId);
                productAdvertisement.setStatus(notPassStatus);
                productAdvertisement.setPrice(payPrice);
                sellerSellerService.writeInSellerAdvertisement(productAdvertisement);
            }
        }
        else
        {
            //支付失败、取消
            if(type.equals("ProductAdvertisement"))
            {
                sellerSellerService.deleteProductAdvertisement(advertisementId);
            }
            else
                sellerSellerService.deleteSellerAdvertisement(advertisementId);
        }
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    //辅助int转string的方法 // 0 - 未判断， 1 - 通过 2 - 未付款
    private String getStatusString(int i)
    {
        if(i == notPassStatus)
        {
            return "not passed!";
        }
        if(i == notPayStatus)
        {
            return "not pay!";
        }

        if(i == passStatus)
            return "passed! Displaying!";
        return "unknow code";
    }

    private String getTypeString(int i)
    {
        if( i == 1)
        {
            return "roll advertisement";
        }
        return "list advertisement";
    }

    private int getTypeInt(String type)
    {
        if("roll advertisement".equals(type))
            return 1;
        return 2;
    }

    private int getDateDifference(Date startDate, Date endDate)
    {
        long e = endDate.getTime();
        long s = startDate.getTime();
        long x1 = e-s;
        long y = x1/(1000*60*60*24);
        int x = (int)((e-s)/(long)(1000*60*60*24));
        return  x;
    }
}

