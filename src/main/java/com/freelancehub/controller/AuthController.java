package com.freelancehub.controller;

import com.freelancehub.dto.auth.AuthResponse;
import com.freelancehub.dto.auth.LoginRequest;
import com.freelancehub.dto.auth.RegisterClientRequest;
import com.freelancehub.dto.auth.RegisterProfessionalRequest;
import com.freelancehub.dto.user.UserResponse;
import com.freelancehub.security.SecurityUtils;
import com.freelancehub.service.AuthService;
import com.freelancehub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación", description = "Endpoints de registro, login y usuario autenticado")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register/client")
    @Operation(summary = "Registrar nuevo cliente (público)")
    public ResponseEntity<AuthResponse> registerClient(
            @Valid @RequestBody RegisterClientRequest request) {
        AuthResponse response = authService.registerClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register/professional")
    @Operation(summary = "Registrar nuevo profesional (público)")
    public ResponseEntity<AuthResponse> registerProfessional(
            @Valid @RequestBody RegisterProfessionalRequest request) {
        AuthResponse response = authService.registerProfessional(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión y obtener JWT (público)")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Datos del usuario autenticado a partir del JWT")
    public ResponseEntity<UserResponse> me() {
        Long currentId = SecurityUtils.getCurrentUserIdOrThrow();
        return ResponseEntity.ok(userService.getById(currentId));
    }
}
