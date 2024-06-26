package teste.aprendendo_basic_auth.security.token.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import teste.aprendendo_basic_auth.security.autenticacao.models.UsuarioModel;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    private Instant ExpiracaoToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String gerarToken(UsuarioModel usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                .withIssuer("api_aprendendo_basic_auth")
                .withSubject(usuario.getEmail())
                .withExpiresAt(ExpiracaoToken())
                .sign(algoritmo);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro na geração do token", e);
        }
       
    }

    public String validarToken(String token){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                .withIssuer("api_aprendendo_basic_auth")
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    
}
