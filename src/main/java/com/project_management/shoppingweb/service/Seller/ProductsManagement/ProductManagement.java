package com.project_management.shoppingweb.service.Seller;

import com.project_management.shoppingweb.domain.Product;

import java.util.LinkedList;

public class ProductManagement {

    private long sellerId;
    private LinkedList<Product> products;

    ProductManagement(long sellerId)
    {
        this.sellerId = sellerId;

    }

    boolean addProduct(Product newProduct)
    {
        return  false;
    }

    boolean changedProduct(Product product)
    {
        return false;
    }

    boolean deleteProduct(Product product)
    {
        return  deleteProduct(product.getProductId());
    }

    boolean deleteProduct(long productId)
    {
        return  false;
    }
}
