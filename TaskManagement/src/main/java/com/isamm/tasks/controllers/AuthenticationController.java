package com.isamm.tasks.controllers;

import com.isamm.tasks.dto.AuthReqDTO;
import com.isamm.tasks.dto.AuthResDTO;
import com.isamm.tasks.dto.RegisterRequestDTO;
import com.isamm.tasks.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
  @Autowired
  private AuthenticationService service;

@PostMapping("/register")
public ResponseEntity<AuthResDTO> register(
		  
    @RequestBody RegisterRequestDTO request
) throws Exception {
	  System.out.println("hello fromm newyork");
  return ResponseEntity.ok().body(service.register(request));
}
  @PostMapping("/authenticate")
  public ResponseEntity<AuthResDTO> authenticate(
      @RequestBody AuthReqDTO request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
