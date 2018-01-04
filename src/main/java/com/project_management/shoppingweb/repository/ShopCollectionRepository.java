package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.ShopCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopCollectionRepository extends JpaRepository<ShopCollection,Long> {
    ShopCollection save(ShopCollection shopCollection);
    List<ShopCollection> findAllByUserId(Long UserID);
    void delete(ShopCollection shopCollection);
    List<ShopCollection> findAllById(Long id);
}
