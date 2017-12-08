package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{
    Address save(Address address);
    List<Address> findByUserId(int userId);
    Address findByAddressId(int addressId);
}
