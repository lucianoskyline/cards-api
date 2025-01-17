package com.cards.controller;

import com.cards.request.WebhooksCardsRequest;
import com.cards.request.WebhooksDeliveryRequest;
import com.cards.service.WebhooksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks")
public class WebhooksController {

    @Autowired
    private WebhooksService webhooksService;


    @PostMapping("/delivery")
    public ResponseEntity delivery(@Valid @RequestBody WebhooksDeliveryRequest request, @RequestHeader("token") String token) {
        webhooksService.verifyDelivery(request, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/card")
    public ResponseEntity card(@Valid @RequestBody WebhooksCardsRequest request, @RequestHeader("token") String token) {
        webhooksService.updateCard(request, token);
        return ResponseEntity.ok().build();
    }

}
