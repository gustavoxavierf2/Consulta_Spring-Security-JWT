package teste.aprendendo_basic_auth.security.autenticacao.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import teste.aprendendo_basic_auth.security.autenticacao.repository.UsuarioRepo;
import teste.aprendendo_basic_auth.security.token.service.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepo usuarioRepo;

    private String recuperarToken(HttpServletRequest request){
        var cabecalho = request.getHeader("Authorization");
        if (cabecalho == null) {
            return null;
        }else{
            return cabecalho.replace("Bearer ", "");  
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = recuperarToken(request);
                if (token != null) {
                    var login = tokenService.validarToken(token);
                    System.out.println("kkk"+ login);
                    UserDetails usuario = usuarioRepo.findByEmail(login);
                    System.out.println(usuario);

                    var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
                filterChain.doFilter(request, response);
    }
    
}
