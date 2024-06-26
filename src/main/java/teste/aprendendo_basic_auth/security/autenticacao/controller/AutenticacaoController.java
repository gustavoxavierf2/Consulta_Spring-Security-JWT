package teste.aprendendo_basic_auth.security.autenticacao.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teste.aprendendo_basic_auth.security.autenticacao.models.UsuarioModel;
import teste.aprendendo_basic_auth.security.token.service.TokenService;

@RestController
@RequestMapping("/autenticar")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/login")
    public String autenticar(@RequestBody UsuarioModel request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        var autenticar = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((UsuarioModel) autenticar.getPrincipal());

        return token;
    }
}
