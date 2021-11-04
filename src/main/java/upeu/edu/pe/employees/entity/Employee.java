/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name") // APELLIDO_PATERNO, AP_PATERNO
    private String lastName; // apellidoPaterno

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private Date hireDate;

    private Integer salary;

    @Column(name = "commission_pct")
    private Integer commissionPct;

    @ManyToOne
    @JoinColumn(name = "depa_id")
    // @JsonBackReference
    private Departament departament;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private boolean estado;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }
}
