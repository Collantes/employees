/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.employees.entity.Employee;

/**
 *
 * @author Desarrollo
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE (firstName like %:query% or lastName  like %:query%)")
    List<Employee> findAll(String query, Sort sort);

    @Query("SELECT e FROM Employee e WHERE (firstName like %:query% or lastName  like %:query%)")
    Page<Employee> findAllParams(String query, Pageable pageable);
    
    /*
    INSERT
    UPDATE
    */

}
