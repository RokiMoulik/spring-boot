package com.ezybytes.controller;

import com.ezybytes.model.Cards;
import com.ezybytes.repository.CardsRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam int id){
        System.out.println("@GetMapping(\"/myCards\")");
        List<Cards> cardsList = cardsRepository.findByCustomerId(id);
        return cardsList;
    }
}
