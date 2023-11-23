package com.example.desafio.dto;

import com.example.desafio.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserLogIn extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UserModel userLogIn;

    public UserLogIn(UserModel userLogIn, Collection<? extends GrantedAuthority> authorities) {
        super(userLogIn.getEmail(), userLogIn.getPassword(), true, true, true, true, authorities);
        this.userLogIn = userLogIn;
    }

    public UserModel getUserLogIn(){
        return this.userLogIn;
    }

}