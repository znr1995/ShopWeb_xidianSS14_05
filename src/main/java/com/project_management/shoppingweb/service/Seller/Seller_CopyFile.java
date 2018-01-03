package com.project_management.shoppingweb.service.Seller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.swing.plaf.PanelUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Seller_CopyFile {

    private String BASEPATH = "C:\\Users\\gsy\\Desktop\\ShopWeb_xidianSS14_05\\src\\main\\resources\\static\\";

    private String FINDPATH = "http://localhost:8888/images/";

    private static Seller_CopyFile seller_copyFile = null;

    public  static Seller_CopyFile getInstance()
    {
        if(seller_copyFile == null)
        {
            seller_copyFile = new Seller_CopyFile();
        }
        return  seller_copyFile;
    }

    public String copyFile(MultipartFile file)
    {

        String newPath = getNewFilename(file);


        try {
            file.transferTo(new File(BASEPATH + newPath));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return FINDPATH + newPath;
    }


    String getRandomNum()
    {
        Random random = new Random();
        long var = random.nextLong();
        if(var < 0)
            var = -var;
        return String.valueOf(var);
    }


    String getNewFilename(MultipartFile  file)
    {
        String newPath;
        do {
            newPath  = getRandomNum() + file.getOriginalFilename();
        }
        while (new File(BASEPATH + newPath).exists());
        return newPath;

    }


    void copyFile(File fromFile,String newPath) throws IOException {
        File toFile = new File(newPath);
        if(!toFile.exists())
            toFile.createNewFile();
        if(toFile.exists()) {
            FileInputStream ins = new FileInputStream(fromFile);
            FileOutputStream out = new FileOutputStream(toFile);
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, n);
            }
            ins.close();
            out.close();
        }
        else
        {
            System.out.println("fail to get exist!");
        }

    }

}
