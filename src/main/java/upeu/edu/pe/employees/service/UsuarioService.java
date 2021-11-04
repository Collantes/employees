/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.service;

import upeu.edu.pe.employees.entity.Usuario;

/**
 *
 * @author Desarrollo
 */
public interface UsuarioService{
    
    public Usuario findByUsername(String username);
    
}
