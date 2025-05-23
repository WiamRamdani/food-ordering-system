package com.wm.response;

import com.wm.model.ROLE;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class AuthResponse {
    private String jwt;
    private String message;
    private ROLE role;
}
