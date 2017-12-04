package com.project_management.shoppingweb.service.Seller.ProductsManagement;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductInformation;
import com.project_management.shoppingweb.service.SellerSQLFunction;

import java.util.LinkedList;

public class ProductManagementService {
    private long sellerId;
    private LinkedList<ProductInformation> products;
    SellerSQLFunction sellerFunction = new SellerSQLFunction();
    public ProductManagementService(long sellerId)
    {
        this.sellerId = sellerId;
        products = sellerFunction.getAllProducts(sellerId);
    }

    public boolean addProduct(ProductInformation newProduct)
    {
        return  sellerFunction.createProductInformation(sellerId, newProduct);
    }

    public boolean changedProduct(ProductInformation product)
    {
        return sellerFunction.changedProducts(product);
    }

    public boolean deleteProduct(ProductInformation product)
    {
        return  deleteProduct(product.getProductId());
    }

    public boolean deleteProduct(long productId)
    {
        return  sellerFunction.deleteProducts(productId);
    }

}
