/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.employees.entity.Departament;

/**
 *
 * @author Desarrollo
 */
@Repository
public interface DepartamentRepository extends CrudRepository<Departament, Long> {
/*
    @Query(value = "{call sp_lista_departamentos()}", nativeQuery = true)
    List<Departament> listaDepartamentos();

    @Query(value = "{call sp_lista_departamento(:idIn)}", nativeQuery = true)
    List<Departament> listaDepartamentoId(@Param("idIn") int idIn);*/
}
