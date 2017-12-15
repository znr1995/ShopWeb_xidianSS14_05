package com.project_management.shoppingweb.controller.Seller.ModifySellerAdvertisement;



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
            ProductAdvertisementPlus productAdvertisementPlus = new ProductAdvertisementPlus();
            productAdvertisementPlus.setAdvertisementId(productAdvertisement.getAdvertisementId());
            productAdvertisementPlus.setDescription(productAdvertisement.getDescription());
            productAdvertisementPlus.setStartDate(sdf.format(productAdvertisement.getStartDate()));
            productAdvertisementPlus.setEndDate(sdf.format(productAdvertisement.getEndDate()));
            productAdvertisementPlus.setStatusString(getStatusString(productAdvertisement.getStatus()));
            productAdvertisementPlus.setTypeString(getTypeString(productAdvertisement.getType()));
            productAdvertisementPluses.add(productAdvertisementPlus);
        }
        for(SellerAdvertisement productAdvertisement : sellerAdvertisements)
        {
            SellerAdvertisementPlus productAdvertisementPlus = new SellerAdvertisementPlus();
            productAdvertisementPlus.setAdvertisementId(productAdvertisement.getAdvertisementId());
            productAdvertisementPlus.setDescription(productAdvertisement.getDescription());
            productAdvertisementPlus.setStartDate(sdf.format(productAdvertisement.getStartDate()));
            productAdvertisementPlus.setEndDate(sdf.format(productAdvertisement.getEndDate()));
            productAdvertisementPlus.setStatusString(getStatusString(productAdvertisement.getStatus()));
            sellerAdvertisementPluses.add(productAdvertisementPlus);
        }
        model.addAttribute("productAdvertisements",productAdvertisementPluses);
        model.addAttribute("sellerAdvertisements",sellerAdvertisementPluses);
        return "/Seller/ModifySellerAdverMainPage";
    }

    @RequestMapping("ReturnBack")
    public String returnToMainPage(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }

    @RequestMapping("ReturnBack1")
    public String returnToPage1(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    //添加广告
    @RequestMapping("AddNewAdvertisement")
    public String jumpToProductAdvertisementPageFormAddNewAdvertisement(Model model)
    {
        model.addAttribute("AddOrModify","Add");
        model.addAttribute("Products",sellerSellerService.getSellerProducts(sellerID));
        model.addAttribute("SellerID",sellerID);
        return "/Seller/AddProductAdvertisementPage";
    }

    //修改产品广告前
    @RequestMapping("ModifyProductAdvertisement")
    public String jumpToProductAdvertisementPage(HttpServletRequest request, Model model)
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("startDate",sdf.format(productAdvertisement.getStartDate()));
        model.addAttribute("endDate",sdf.format(productAdvertisement.getEndDate()));
        model.addAttribute("pictureUrl",productAdvertisement.getPictureUrl());
        model.addAttribute("description",productAdvertisement.getDescription());
        model.addAttribute("IsProductAdvertisement","true");
        model.addAttribute("advertisementType","Product Advertisement");
        return "/Seller/ModifyProductAdvertisementPage";
    }

    //修改商家广告前
    @RequestMapping("ModifySellerAdvertisement")
    public String jumpToSellerAdvertisement(HttpServletRequest request, Model model)
    {
        long advertisementId = Long.valueOf(request.getParameter("modify"));
        SellerAdvertisement sellerAdvertisement = sellerSellerService.getSellerAdvertisement(advertisementId);
        model.addAttribute("AddOrModify","Modify");
        model.addAttribute("SellerID",sellerID);
        model.addAttribute("status",
                sellerAdvertisement.getStatus() == 1 ? "not passed yet!" : "passed,on display!");
      //  model.addAttribute("SellerAdvertisement",advertisementId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("startDate",sdf.format(sellerAdvertisement.getStartDate()));
        model.addAttribute("endDate",sdf.format(sellerAdvertisement.getEndDate()));
        model.addAttribute("pictureUrl",sellerAdvertisement.getPictureUrl());
        model.addAttribute("description",sellerAdvertisement.getDescription());
        model.addAttribute("IsProductAdvertisement","false");
        model.addAttribute("advertisementType","Seller Advertisement");
        return "/Seller/ModifyProductAdvertisementPage";
    }

    //修改或添加广告
    @RequestMapping(value = "ModifyAdvertisement",method = RequestMethod.POST)
    public String advertisementHandler(@RequestParam(value = "pictureUrl")MultipartFile file,
                                       HttpServletRequest request, RedirectAttributes attributes)
    {
        int option = Integer.valueOf(request.getParameter("advertisementType"));
        Date start = new Date();Date end= new Date();
        String pictureUrl = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            start = sdf.parse(startDate);
            end = sdf.parse(endDate);
        }
        catch (Exception e)
        {

        }
         try {
            pictureUrl = Seller_CopyFile.getInstance().copyFile(file);
         }
        catch (Exception e)
        {

        }
        if(1 == option)
        {
            return modifyProductAdvertisement(pictureUrl,start,end,request,attributes);
            //product advertisement
        }
        else
        {
            return modifySellerAdvertisement(pictureUrl,start,end,request,attributes);
            //shopadvertisement
        }
    }

    //增加或修改商品广告信息
    public String modifyProductAdvertisement(String pictureUrl,Date startDate, Date endDate,HttpServletRequest request, RedirectAttributes attributes)
    {
        ProductAdvertisement productAdvertisement = null;
        String addOrModify = request.getParameter("AddOrModify");
        if(addOrModify.equals("Add"))
        {
            //新建商品广告
            //TODO:广告价格没有设置
            productAdvertisement = new ProductAdvertisement();
            productAdvertisement.setStatus(1);
            long productId = Long.valueOf(request.getParameter("productIDs"));
            int locationId = Integer.valueOf(request.getParameter("type"));
            productAdvertisement.setProductId(productId);
            productAdvertisement.setType(locationId);
            productAdvertisement.setStartDate(startDate);
            productAdvertisement.setEndDate(endDate);
            productAdvertisement.setSellerName(sellerSellerService.getSellerById(sellerID).getUsername());
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
        productAdvertisement.setDescription(request.getParameter("description"));



        sellerSellerService.writeInProductAdvertisement(productAdvertisement);



        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }


    //增加或修改商家广告信息
    public String modifySellerAdvertisement(String pictureUrl,Date startDate, Date endDate,HttpServletRequest request, RedirectAttributes attributes)
    {
        SellerAdvertisement sellerAdvertisement = null;
        String addOrModify = request.getParameter("AddOrModify");
        if(addOrModify.equals("Add"))
        {
            //新建商家广告
            //TODO:广告价格没有设置
            sellerAdvertisement = new SellerAdvertisement();
            sellerAdvertisement.setStartDate(startDate);
            sellerAdvertisement.setEndDate(endDate);
            sellerAdvertisement.setSellerName(sellerSellerService.getSellerById(sellerID).getUsername());
            sellerAdvertisement.setSellerId(sellerID);
            sellerAdvertisement.setStatus(1);
        }
        else
        {
            //修改广告
            long advertisementId = Long.valueOf(request.getParameter("ProductAdvertisement"));
            sellerAdvertisement = sellerSellerService.getSellerAdvertisement(advertisementId);
        }

        // 修改广告只允许修改描述和图片
        if(pictureUrl != null)
            sellerAdvertisement.setPictureUrl(pictureUrl);
        sellerAdvertisement.setDescription(request.getParameter("description"));


        sellerSellerService.writeInSellerAdvertisement(sellerAdvertisement);

        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    private String getStatusString(int i)
    {
        if(i == 1)
        {
            return "not passed!";
        }
        return "passed! Displaying!";
    }

    private String getTypeString(int i)
    {
        if( i == 1)
        {
            return "roll advertisement";
        }
        return "list advertisement";
    }
}

