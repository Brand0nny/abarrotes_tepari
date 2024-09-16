package com.brand0nny.springboot.web.abarrotes_tepari.services.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.Product;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Address;
import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.User;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.AddressRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.repositories.UserRepository;
import com.brand0nny.springboot.web.abarrotes_tepari.services.AddressService;
@Transactional
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Address createAddress(Address address, String username) throws Exception {
       
        Address addressBuilder = Address.builder()
        .city(address.getCity())
        .country(address.getCountry())
        .number(address.getNumber())
        .postalCode(address.getPostalCode())
        .state(address.getState())
        .street(address.getStreet())
        .build();
        Address newAddress=addressRepository.save(addressBuilder);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.getAddresses().add(newAddress);
            userRepository.save(user);

        }
        return newAddress;
       
       
    }

    @Override
    public Iterable<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<List<Long>> getAllAddressFromUser(Long userId) {
        System.out.println(addressRepository.findAllAddressIdFromUserById(userId).toString());
       return addressRepository.findAllAddressIdFromUserById(userId);
    }

    @Override
    public Optional<Address> getAddressById(Long id) {

        return addressRepository.findById(id);
    }


}
