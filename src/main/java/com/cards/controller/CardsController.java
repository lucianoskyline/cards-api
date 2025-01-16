package com.cards.controller;

import com.cards.request.CardsCreateRequest;
import com.cards.service.CardsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CardsCreateRequest request) {
        return ResponseEntity.ok(cardsService.create(request));
    }

}
