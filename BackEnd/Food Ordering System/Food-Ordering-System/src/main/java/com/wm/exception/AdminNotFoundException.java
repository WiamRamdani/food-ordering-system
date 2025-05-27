package com.wm.exception;

public class AdminNotFoundException extends RuntimeException {
    
    public AdminNotFoundException() {
        super("Admin non trouvé");
    }

    public AdminNotFoundException(String message) {
        super(message);
    }
}
