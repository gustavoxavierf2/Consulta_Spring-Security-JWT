package teste.aprendendo_basic_auth.security.autenticacao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import teste.aprendendo_basic_auth.security.autenticacao.repository.UsuarioRepo;

@Service
public class AutorizacaoService implements UserDetailsService{
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return usuarioRepo.findByEmail(username);
    }
    
}
