package queimando.iftm.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import queimando.iftm.model.Usuario;


public class QueimandoSecurityUserDetails implements UserDetails {

  private Usuario usuario;

  public QueimandoSecurityUserDetails(Usuario usuario) {
    this.usuario = usuario;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String[] papeis = usuario.getPapeis().split(",");
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (String papel : papeis) {
      authorities.add(new SimpleGrantedAuthority(papel));
    }
    return authorities;
  }

  @Override
  public String getPassword() {
    return usuario.getSenha();
  }

  @Override
  public String getUsername() {
    return usuario.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
