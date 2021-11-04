/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Desarrollo
 */
@Data
@Entity
@Table(name = "roles")
public class Rol implements Serializable{
    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolId;

    @Column(name = "rol_nombre", unique = true, length = 20)
    private String rolNombre;
    
    private static final long serialVersionUID = 1L;
}
