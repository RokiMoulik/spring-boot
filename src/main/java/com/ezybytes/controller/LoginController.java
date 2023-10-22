package com.ezybytes.controller;

import com.ezybytes.model.Customer;
import com.ezybytes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String>registerUser(@RequestBody Customer customer){
        System.out.println("@PostMapping(\"/register\")");

        Customer savedCustomer = null;
        ResponseEntity responseEntity = null;

        try{
            customer.setPwd(passwordEncoder.encode(customer.getPwd())); // covert to hash value
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0){
                responseEntity = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully created");
            }
        }
        catch (Exception ex){
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to "+ ex);
        }
        return responseEntity;
    }

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        System.out.println("@RequestMapping(\"/user\")");
        List<Customer> customerList = customerRepository.findByEmail(authentication.getName());
        if(customerList.size() > 0){
            return customerList.get(0);
        }
        else{
            return null;
        }
    }
}
