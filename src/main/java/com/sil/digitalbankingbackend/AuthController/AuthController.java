package com.sil.digitalbankingbackend.AuthController;




import com.sil.digitalbankingbackend.dtos.SigninRequest;
import com.sil.digitalbankingbackend.dtos.SignupRequest;
import com.sil.digitalbankingbackend.response.AuthResponse;
import com.sil.digitalbankingbackend.response.Response;
import com.sil.digitalbankingbackend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<Response> signup(@RequestBody SignupRequest request) {
        return (authService.signup(request));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<Response> signin(@RequestBody SigninRequest request) {
        return (authService.signin(request));
    }
}
