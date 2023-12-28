package com.isamm.tasks.services;


import com.isamm.tasks.dto.AuthReqDTO;
import com.isamm.tasks.dto.AuthResDTO;
import com.isamm.tasks.dto.RegisterRequestDTO;

public interface AuthenticationService {
	 public AuthResDTO register(RegisterRequestDTO request) throws Exception;
	 public AuthResDTO authenticate(AuthReqDTO request) ;

}
