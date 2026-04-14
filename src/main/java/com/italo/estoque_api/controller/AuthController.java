package com.italo.estoque_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italo.estoque_api.dto.AuthDTOs;
import com.italo.estoque_api.model.Usuario;
import com.italo.estoque_api.repository.UsuarioRepository;
import com.italo.estoque_api.security.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthDTOs.LoginResponseDTO> login(@RequestBody AuthDTOs.AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new AuthDTOs.LoginResponseDTO(token));
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody AuthDTOs.RegisterDTO data) {
        if (this.repository.findByLogin(data.login()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já cadastrado.");
        }
        String senhaCriptografada = passwordEncoder.encode(data.senha());
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(data.login());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setRole(data.role());
        this.repository.save(novoUsuario);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}