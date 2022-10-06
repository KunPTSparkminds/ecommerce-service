package net.sparkminds.service;

import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;
import net.sparkminds.service.dto.response.ReviewerResponseDTO;

public interface AuthService {
	LoginResponse login(LoginRequestDTO loginRequestDTO);
    String logout(String jwt);
	ReviewerResponseDTO register(RegisterRequestDTO registerRequestDTO);
}
