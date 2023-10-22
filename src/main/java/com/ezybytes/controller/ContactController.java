package com.ezybytes.controller;

import com.ezybytes.model.Contact;
import com.ezybytes.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/contact")
    public Contact saveContactInQueryDetails(@RequestBody Contact contact){

        System.out.println("@PostMapping(\"/contact\")");

        contact.setContactId(getServiceRequestNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
     }

    private String getServiceRequestNumber() {
        Random random = new Random();
        int ranNumber = random.nextInt(999999999-9999) + 9999;
        return "SR" + ranNumber;
    }
}
