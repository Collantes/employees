/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.employees.entity.Departament;
import upeu.edu.pe.employees.repository.DepartamentRepository;
import upeu.edu.pe.employees.service.DepartamentService;

/**
 *
 * @author Desarrollo
 */
@Service
public class DepartamentServiceImpl implements DepartamentService {

    @Autowired
    DepartamentRepository departamentRepository;

    @Override
    public List<Departament> findAll() {
        return (List<Departament>) departamentRepository.findAll();
    }

    @Override
    public Departament findById(Long id) {
        return departamentRepository.findById(id).orElse(null);
    }

    @Override
    public Departament save(Departament departament) {
        return departamentRepository.save(departament);
    }

    @Override
    public void delete(Departament departament) {
        departamentRepository.delete(departament);
    }

}
