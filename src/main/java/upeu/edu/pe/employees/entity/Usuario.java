/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.entity;

import ch.qos.logback.classic.db.names.ColumnName;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author Desarrollo
 */
@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @Column(name = "usua_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuaId;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 60)
    private String password;
    private Boolean enabled;
    
    private String nombre;
    private String apellido;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", 
            joinColumns = @JoinColumn(name = "usua_id"), 
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usua_id","rol_id"})})
    private List<Rol> roles;

    private static final long serialVersionUID = 1L;
    
    
}
