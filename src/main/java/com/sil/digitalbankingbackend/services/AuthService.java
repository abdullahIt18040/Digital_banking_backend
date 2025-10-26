package com.sil.digitalbankingbackend.services;


import com.sil.digitalbankingbackend.dtos.SigninRequest;
import com.sil.digitalbankingbackend.dtos.SignupRequest;
import com.sil.digitalbankingbackend.entities.User;
import com.sil.digitalbankingbackend.repositories.UserRepository;
import com.sil.digitalbankingbackend.response.AuthResponse;
import com.sil.digitalbankingbackend.response.Response;
import com.sil.digitalbankingbackend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<Response> signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return Response.getResponseData(HttpStatus.BAD_REQUEST, "Email already exists!", null);        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return Response.getResponseData(HttpStatus.OK,"User registered successfully!",token,request);
//        return new AuthResponse(token, "User registered successfully!");
    }
    public ResponseEntity<Response> signin(SigninRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return Response.getResponseData(HttpStatus.BAD_REQUEST, "Invalid Credentials", null);
            }

            user.setPassword(null);
            String token = jwtUtil.generateToken(user.getEmail());
            return Response.getResponseData(HttpStatus.OK, "User Login successfully!", token, user);

        } catch (Exception ex) {
            return Response.getResponseData(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        }
    }
}
