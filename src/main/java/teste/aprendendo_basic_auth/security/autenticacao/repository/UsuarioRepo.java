package teste.aprendendo_basic_auth.security.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.aprendendo_basic_auth.security.autenticacao.models.UsuarioModel;


public interface UsuarioRepo extends JpaRepository<UsuarioModel, Long>{
    UsuarioModel findByEmail(String email);
}
