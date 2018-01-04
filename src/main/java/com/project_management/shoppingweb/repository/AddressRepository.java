package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{
    List<Address> findAllByUserId(Long UserID);
    List<Address> findAllByAddressId(Long AddressID);
    Address save(Address address);
}
