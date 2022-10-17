package net.sparkminds.service;

import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;
import net.sparkminds.service.dto.response.UserResponseDTO;

public interface AuthService {
	LoginResponse login(LoginRequestDTO loginRequestDTO);
    String logout(String jwt);
	UserResponseDTO register(RegisterRequestDTO registerRequestDTO);
	UserResponseDTO getUserDetail(String token);
}
