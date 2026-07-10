package com.example.api.controller;

import com.example.api.model.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {
    //
    @Autowired
    private EmployeeService employeeService;

    /**
     * Create - add a new employee
     * @param employee An object employee
     * @return The saved employee object saved
     */
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    /**
     * Recuperer tous les employées
     * @return Une list d'employée
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Recupérer un employé
     * @param id identifiant de l'employé
     * @return l'objet Employee
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        return employee.orElse(null);
    }

    /**
     * Suppression d'un employé à partir de son id
     * @param id identifiant de l'employé
     */
    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable("id") final Long id) {
        employeeService.deleteEmployeeById(id);
    }

    /**
     * Suppréssion de tous les employés
     */
    @DeleteMapping("/delete")
    public void deleteEmployees() {
        employeeService.deleteEmployees();
    }
}
