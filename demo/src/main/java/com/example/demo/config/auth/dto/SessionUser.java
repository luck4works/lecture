package com.example.demo.config.auth.dto;
import java.io.Serializable;

import com.example.demo.domain.user.Account;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
    private String email;
    private String picture;
 
    public SessionUser(Account user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}