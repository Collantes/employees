/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Desarrollo
 */
@Data
@Entity
@Table(name = "departaments")
public class Departament {

    @Id
    @Column(name = "depa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depaId;

    @Column(name = "depa_nombre")
    private String depaNombre;

    @Column(name = "depa_abreviatura")
    private String depaAbreviatura;

    @Column(name = "depa_estado")
    private Boolean depaEstado;
/*
    @OneToMany(mappedBy = "departament")
    @JsonManagedReference
    private List<Employee> employees;*/
}
