package com.lilium.springangular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorService {

    private final WebSocketService webSocketService;

    @Autowired
    public ErrorService(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    public void displayError(final String errorMessage) {
        webSocketService.sendMessage("error", errorMessage);
    }
}
