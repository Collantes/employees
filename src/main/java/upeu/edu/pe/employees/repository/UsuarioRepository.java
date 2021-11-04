/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.employees.entity.Usuario;

/**
 *
 * @author Desarrollo
 */

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
