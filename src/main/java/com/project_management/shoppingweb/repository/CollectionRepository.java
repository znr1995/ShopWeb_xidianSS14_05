package com.project_management.shoppingweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.project_management.shoppingweb.domain.Collection;
import java.util.List;

public interface CollectionRepository extends JpaRepository <Collection,Long> {
    Collection save(Collection collection);
    List<Collection> findByUserId(int userID);
    Collection findByCollectionId(int collectionId);

}
