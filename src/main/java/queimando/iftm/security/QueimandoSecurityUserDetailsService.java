package queimando.iftm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import queimando.iftm.model.Usuario;
import queimando.iftm.repository.UsuarioRepository;

public class QueimandoSecurityUserDetailsService implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.buscaPorEmail(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuário não autenticado!");
    }
    System.out.println(usuario.getPapeis());
    return new QueimandoSecurityUserDetails(usuario);
  }
}
