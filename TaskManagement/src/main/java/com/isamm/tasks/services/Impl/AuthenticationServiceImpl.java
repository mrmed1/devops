package com.isamm.tasks.services.Impl;


import javax.naming.AuthenticationException;

import com.isamm.tasks.config.JwtService;
import com.isamm.tasks.dto.AuthReqDTO;
import com.isamm.tasks.dto.AuthResDTO;
import com.isamm.tasks.dto.RegisterRequestDTO;
import com.isamm.tasks.models.Member;
import com.isamm.tasks.repository.MemberRepository;
import com.isamm.tasks.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	 @Autowired 
	  private  PasswordEncoder passwordEncoder;
	 @Autowired
	  private MemberRepository repository;
	 @Autowired
	  private JwtService jwtService;
	 @Autowired
	  private final AuthenticationManager authenticationManager;
	 @Override
	 public AuthResDTO register(RegisterRequestDTO request) {
	     try {
	         if (repository.findByUsername(request.getUserName()).isPresent()) {
	        	  return AuthResDTO.builder()
	 	                 .msg("Username already exists")
	 	                 .build();
	         }

	         var member = Member.builder()
	                 .username(request.getUserName())
	                 .password(passwordEncoder.encode(request.getPassword()))
	                 .role(request.getRole())
	                 .build();

	         var savedUser = repository.save(member);
	         var jwtToken = jwtService.generateToken(member);

	         return AuthResDTO.builder()
	                 .msg("User created successfully")
	                 .accessToken(jwtToken)
	                 .build();
	     }   catch (Exception e) {
	         
	         return AuthResDTO.builder()
	                 .msg("User creation failed")
	                 .build();
	     }
	 }


@Override
public AuthResDTO authenticate(AuthReqDTO request) {
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
    } catch (BadCredentialsException e) {
        // Handle bad credentials (invalid username or password)
        return AuthResDTO.builder()
                .msg("Invalid username or password")
                .build();
    } catch (Exception e) {
        // Handle other authentication exceptions
        return AuthResDTO.builder()
                .msg("Authentication failed")
                .build();
    }

    var user = repository.findByUsername(request.getUserName())
            .orElseThrow(); // Assuming user is present after successful authentication

    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);

    return AuthResDTO.builder()
            .msg("Login successfully")
            .accessToken(jwtToken)
            .build();
}

}
