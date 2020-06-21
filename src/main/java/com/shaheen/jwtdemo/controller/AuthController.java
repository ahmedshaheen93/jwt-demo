package com.shaheen.jwtdemo.controller;

import com.shaheen.jwtdemo.model.JwtResponse;
import com.shaheen.jwtdemo.model.SignInRequest;
import com.shaheen.jwtdemo.security.TokenUtil;
import com.shaheen.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthController {
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/auth")
    public JwtResponse signIn(@RequestBody @NotNull SignInRequest signInRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
    @PostMapping(value = "/refresh")
    public JwtResponse refresh(Principal principal) {
        UserDetails userDetails = userService.loadUserByUsername(principal.getName());
        String token = tokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
