/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.service;

import java.util.HashMap;
import java.util.List;
import upeu.edu.pe.employees.entity.Employee;

/**
 *
 * @author Desarrollo
 */
public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(Long id);

    public Employee save(Employee employee);

    public void delete(Employee employee);
    
    public void deleteById(Long id);

    public List<Employee> findAll(String query, String sortBy);

    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy);
}
