/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upeu.edu.pe.employees.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upeu.edu.pe.employees.entity.Departament;
import upeu.edu.pe.employees.service.DepartamentService;

/**
 *
 * @author Desarrollo
 */
@RestController
@RequestMapping("api/departament")
@Api(value = "Microservicios de Gestion de Departamentos", description = "Microservicio de Departamento")
public class DepartamentController {

    @Autowired
    private DepartamentService departamentService;

    @ApiOperation(value = "Lista de Departamentos"/*,authorizations = { @Authorization(value = "apiKey") }*/)//,  
    @GetMapping
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "El empleado se ha registrado correctamente.");
        result.put("data", departamentService.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Crea Departamento")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Departament departament, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Departament data = departamentService.save(departament);

        result.put("success", true);
        result.put("message", "El empleado se ha registrado correctamente.");
        result.put("data", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Actualiza un Departamento")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Departament departament, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Departament data = departamentService.findById(departament.getDepaId());
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe departamento con Id: " + departament.getDepaId());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            departamentService.save(departament);
            result.put("success", true);
            result.put("message", "Se ha actualizado los datos del departamento.");
            result.put("data", departament);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene Datos del Departamento")
    @GetMapping(value = "/{depaId}")
    public ResponseEntity<?> findById(@PathVariable(value = "depaId") Long depaId, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Departament data = departamentService.findById(depaId);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe departamento con Id: " + depaId);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        result.put("success", true);
        result.put("message", "Se ha encontrado el registro.");
        result.put("data", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina un Departamento")
    @DeleteMapping(value = "/{depaId}")
    public ResponseEntity<?> delete(@PathVariable(value = "depaId") Long depaId, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Departament data = departamentService.findById(depaId);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe Departamento con id: " + depaId);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            data.setDepaEstado(false);
            departamentService.save(data);
            result.put("success", true);
            result.put("message", "Se ha eliminado los datos del registro.");
            result.put("data", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
