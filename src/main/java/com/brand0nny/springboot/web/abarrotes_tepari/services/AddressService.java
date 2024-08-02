package com.brand0nny.springboot.web.abarrotes_tepari.services;

import java.util.List;
import java.util.Optional;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;

public interface AddressService {
public Address createAddress(Address address,  String username) throws Exception;
public Iterable<Address> getAllAddress();
public Optional<List<Long>> getAllAddressFromUser(Long userId);
public Optional<Address> getAddressById(Long id);
}
