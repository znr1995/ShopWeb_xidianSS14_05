package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long>{
    Address save(Address address);
    List<Address> findByUserId(int userId);
    Address findByAddressId(int addressId);
}
