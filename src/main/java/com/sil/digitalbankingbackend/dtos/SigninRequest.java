package com.sil.digitalbankingbackend.dtos;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {
    private String email;
    private String password;
}
