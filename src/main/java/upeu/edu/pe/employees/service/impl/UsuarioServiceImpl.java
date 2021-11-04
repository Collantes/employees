/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upeu.edu.pe.employees.entity.Usuario;
import upeu.edu.pe.employees.repository.UsuarioRepository;
import upeu.edu.pe.employees.service.UsuarioService;

/**
 *
 * @author Desarrollo
 */
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            logger.error("error en el login, no existe usuario");
            throw new UsernameNotFoundException("error en el login");
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre()))
                .peek(authority -> logger.info("Rol: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

}
