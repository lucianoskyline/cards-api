package com.cards.controller;

import com.cards.request.AccountsCreateRequest;
import com.cards.service.AccountsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody AccountsCreateRequest request) {
        return ResponseEntity.ok(accountsService.create(request));

    }

}
