package com.project_management.shoppingweb.service;

import com.project_management.shoppingweb.domain.Address;
import com.project_management.shoppingweb.repository.AddressRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressService {
    @Resource
    AddressRepository addressRepository;

    public List<Address> findAllByUserId(Long UserID){
        return addressRepository.findAllByUserId(UserID);
    }
    public List<Address> findAllByAddressId(Long AddressID){
        return addressRepository.findAllByAddressId(AddressID);
    }

    public Address save(Address adress) {
        return addressRepository.save(adress);
    }
}
