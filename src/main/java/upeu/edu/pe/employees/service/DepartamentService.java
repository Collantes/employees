/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.service;

import java.util.List;
import upeu.edu.pe.employees.entity.Departament;

/**
 *
 * @author Desarrollo
 */
public interface DepartamentService {

    public List<Departament> findAll();

    public Departament findById(Long id);

    public Departament save(Departament departament);

    public void delete(Departament departament);
}
