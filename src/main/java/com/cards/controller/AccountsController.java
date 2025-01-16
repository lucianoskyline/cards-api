package com.cards.controller;

import com.cards.request.AccountsCancelRequest;
import com.cards.request.AccountsCreateRequest;
import com.cards.service.AccountsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody AccountsCreateRequest request) {
        return ResponseEntity.ok(accountsService.create(request));
    }

    @DeleteMapping
    public ResponseEntity cancel(@Valid @RequestBody AccountsCancelRequest request) {
        return ResponseEntity.ok(accountsService.cancel(request));
    }

}
