/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upeu.edu.pe.employees.entity.Employee;
import upeu.edu.pe.employees.repository.EmployeeRepository;
import upeu.edu.pe.employees.service.EmployeeService;

/**
 *
 * @author Desarrollo
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> findAll(String query, String sortBy) {
        Sort sort;
        if (!sortBy.equals("")) {/*sortBy = COLUMNA|ASC O DESC idEmployee|ASC*/
            String sortColumn = sortBy.split("\\|")[0];
            String sortDirection = sortBy.split("\\|")[1].toUpperCase();
            sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
        } else {
            sort = Sort.by(Direction.ASC, "idEmployee");
        }
        return employeeRepository.findAll("%" + query.toUpperCase() + "%", sort);
    }

    @Override
    public HashMap<String, Object> findAll(String query, int page, int limit, String sortBy) {
        HashMap<String, Object> result = new HashMap<>();
        Pageable pageable;
        if (!sortBy.equals("")) {
            String sortColumn = sortBy.split("\\|")[0];
            String sortDirection = sortBy.split("\\|")[1].toUpperCase();

            Sort sort = Sort.by(sortDirection.equals("DESC") ? Direction.DESC : Direction.ASC, sortColumn);
            pageable = PageRequest.of(page - 1, limit, sort);
        } else {
            Sort sort = Sort.by(Direction.ASC, "idEmployee");
            pageable = PageRequest.of(page - 1, limit, sort);
        }

        Page<Employee> data = employeeRepository.findAllParams("%" + query.toUpperCase() + "%", pageable);

        if (!data.getContent().isEmpty()) {
            result.put("items", data.getContent());
        } else {
            result.put("items", new ArrayList<Employee>());
        }
        result.put("totalPage", data.getTotalPages());
        result.put("totalRows", data.getNumberOfElements());
        result.put("page", page);
        result.put("limit", limit);
        return result;
    }
}
