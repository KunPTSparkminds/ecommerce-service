package net.sparkminds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.AuthService;
import net.sparkminds.service.JwtUserDetailsService;
import net.sparkminds.service.dto.request.LoginRequestDTO;
import net.sparkminds.service.dto.request.RegisterRequestDTO;
import net.sparkminds.service.dto.response.LoginResponse;
import net.sparkminds.service.dto.response.UserResponseDTO;



@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUserDetailsService jwtUserDetailsService;
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO,
            HttpServletRequest request) {
        return ResponseEntity.ok(authService.register(registerRequestDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        authService.logout(token.split(" ")[1]);
        return ResponseEntity.noContent().build();
    }
    

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
    
    @GetMapping("/user-detail")
    public ResponseEntity<UserResponseDTO> getUserDetail(@RequestHeader HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION).split(" ")[1];
        return ResponseEntity.ok(authService.getUserDetail(token));
    }
}
