package com.project_management.shoppingweb.service.Seller.ProductsManagement;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductInformation;

import java.util.LinkedList;

public class ProductManagementService {
    private long sellerId;
    private LinkedList<ProductInformation> products;

    public ProductManagementService(long sellerId)
    {
        this.sellerId = sellerId;
        products = getAllProducts(sellerId);
    }

    public boolean addProduct(ProductInformation newProduct)
    {
        return  createProductInformation(sellerId, newProduct);
    }

    public boolean changedProduct(ProductInformation product)
    {
        return changedProducts(product);
    }

    public boolean deleteProduct(ProductInformation product)
    {
        return  deleteProduct(product.getProductId());
    }

    public boolean deleteProduct(long productId)
    {
        return  deleteProducts(productId);
    }

}
