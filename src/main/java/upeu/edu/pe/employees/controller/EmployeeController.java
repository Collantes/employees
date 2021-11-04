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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import upeu.edu.pe.employees.entity.Employee;
import upeu.edu.pe.employees.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
//@CrossOrigin(origins = {"http://localhost:4200"})
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@Api(value = "Microservicios de Gestion de Empleados", description = "Microservicio de Empleado")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Lista de Empleados"/*,authorizations = { @Authorization(value = "apiKey") }*/)//,  
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "-1") int limit,
            @RequestParam(value = "sortBy", required = false, defaultValue = "") String sortBy,
            HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "Consulta correcta");
        /*
        for (Employee x: employeeService.findAll()){
            x.setEstado(true);
            employeeService.save(x);
        }
        */
        if (query.equals("") && limit == -1 && "".equals(sortBy)) {
            result.put("data", employeeService.findAll());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (!query.equals("") && page == -1 && limit == -1 && "".equals(sortBy)) {
            result.put("data", employeeService.findAll(query, ""));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (!query.equals("") && page == -1 && limit == -1 && !"".equals(sortBy)) {
            result.put("data", employeeService.findAll(query, sortBy));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (limit != -1 && page == -1) {
            return new ResponseEntity<>(new Exception("Parámetro page es requerido"), HttpStatus.CONFLICT);
        } else if (page != -1 && limit == -1) {
            return new ResponseEntity<>(new Exception("Parámetro limit es requerido"), HttpStatus.CONFLICT);
        } else {
            result.put("data", employeeService.findAll(query, page, limit, sortBy));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Crea Empleado")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Employee employee, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Employee data = employeeService.save(employee);

        result.put("success", true);
        result.put("message", "El empleado se ha registrado correctamente.");
        result.put("data", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Actualiza un Empleado")
    @PutMapping("/{idEmployee}")
    public ResponseEntity<?> update(@PathVariable(value = "idEmployee") Long idEmployee, @RequestBody Employee employee, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Employee data = employeeService.findById(idEmployee);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe empleado con Id: " + idEmployee);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            employeeService.save(employee);
            result.put("success", true);
            result.put("message", "Se ha actualizado los datos del empleado.");
            result.put("data", employee);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene Datos del Empleado")
    @GetMapping(value = "/{idEmployee}")
    public ResponseEntity<?> findById(@PathVariable(value = "idEmployee") Long idEmployee, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Employee data = employeeService.findById(idEmployee);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe empleado con Id: " + idEmployee);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        result.put("success", true);
        result.put("message", "Se ha encontrado el registro.");
        result.put("data", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Obtiene Datos del Empleado")
    @GetMapping(value = "/nombre/{idEmployee}")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "idEmployee") Long idEmployee, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Employee data = employeeService.findById(idEmployee);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe empleado con Id: " + idEmployee);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        result.put("success", true);
        result.put("message", "Se ha encontrado el registro.");
        result.put("data", data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Elimina un Empleado")
    @DeleteMapping(value = "/{idEmployee}")
    public ResponseEntity<?> delete(@PathVariable(value = "idEmployee") Long idEmployee, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        Employee data = employeeService.findById(idEmployee);
        if (data == null) {
            result.put("success", false);
            result.put("message", "No existe División con id: " + idEmployee);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        try {
            data.setEstado(false);
            employeeService.save(data);
            result.put("success", true);
            result.put("message", "Se ha eliminado los datos del registro.");
            result.put("data", data);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(new Exception(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @GetMapping("/employee")
    public List<Employee> index() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Employee employee = null;
        Map<String, Object> response = new HashMap<>();
        try {
            employee = this.employeeService.findById(id);
            if (employee == null) {
                response.put("mensaje", "El empleado con ID: " + id.toString() + " no existe en la base de datos!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error a realizar la consulta");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        employee.setCreateAt(new Date());
        this.employeeService.save(employee);
        return employee;
    }

    // EDITAR
    // Opción 1: Enviar todo desde la vista (Cliente)
    // Opción 2: Asignar los valores en el controller
    // Opción 3: Personalizar el Repository con @Query
    @PutMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee update(@RequestBody Employee employee, @PathVariable Long id) {
        Employee employeeCurrent = this.employeeService.findById(id);
        employeeCurrent.setFirstName(employee.getFirstName());
        employeeCurrent.setLastName(employee.getLastName());
        this.employeeService.save(employeeCurrent);
        return employeeCurrent;
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        this.employeeService.delete(employee);
    }
     */
}
