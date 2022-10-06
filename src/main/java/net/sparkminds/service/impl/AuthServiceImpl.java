package net.sparkminds.service.impl;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.config.JwtTokenUtil;
import net.sparkminds.entity.User;
import net.sparkminds.repository.UserRepository;
import net.sparkminds.service.AuthService;
import net.sparkminds.service.JwtUserDetailsService;
import net.sparkminds.service.RedisService;
import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;
import net.sparkminds.service.dto.response.ReviewerResponseDTO;
import net.sparkminds.service.mapper.ReviewerMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
	private final JwtTokenUtil jwtTokenUtil;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JwtUserDetailsService jwtUserDetailsService;
	private final RedisService redisService;
	private final ReviewerMapper reviewerMapper;
	
	

	@Override
	@Transactional
	public LoginResponse login(LoginRequestDTO loginRequestDTO) {
		Optional<User> user = userRepository.findByEmail(loginRequestDTO.getEmail());
		boolean isMatchesPassword = bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(),
				user.get().getPassword());
		if (isMatchesPassword == false) {
			throw new EntityNotFoundException("Password or user name not correct");
		}
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequestDTO.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		LoginResponse tokenResponse = new LoginResponse();
		tokenResponse.setJwtToken(token);
		
		return tokenResponse;
	}

	@Override
	@Transactional
	public String logout(String jwt) {
		long diffInMillies = jwtTokenUtil.getExpirationDateFromToken(jwt).getTime() - new Date().getTime();
		redisService.cacheJWT(jwt, diffInMillies);
		
		return "Logout Success";
	}

	@Override
	@Transactional
	public ReviewerResponseDTO register(RegisterRequestDTO registerRequestDTO) {
		if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
		   return null;
		};
		User reviewer = new User();
		reviewer.setEmail(registerRequestDTO.getEmail());
		reviewer.setName(registerRequestDTO.getName());
		reviewer.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.getPassword()));
		userRepository.save(reviewer);

		return reviewerMapper.entityToResponse(reviewer);
	}

}
