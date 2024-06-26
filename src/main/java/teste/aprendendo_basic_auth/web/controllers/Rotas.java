package teste.aprendendo_basic_auth.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import teste.aprendendo_basic_auth.security.autenticacao.models.UsuarioModel;
import teste.aprendendo_basic_auth.security.autenticacao.repository.UsuarioRepo;


@RestController
@RequestMapping("/rotas")
public class Rotas {
  @Autowired
  private UsuarioRepo usuarioRepo;
 
    @PostMapping("/registrar")
    public UsuarioModel rotaPrivada(@RequestBody UsuarioModel request) {
      var passwordEncoder = new BCryptPasswordEncoder().encode(request.getSenha());
      UsuarioModel novoUsuario = new UsuarioModel(request.getEmail(), passwordEncoder, request.getRole());
      
      return usuarioRepo.save(novoUsuario); 
    }

    @GetMapping("/privada")
    public String rotaPrivada() {
      return "Rota Privada"; 
    }
}
