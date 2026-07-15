package com.example.api.controller;

import com.example.api.model.Employee;
import com.example.api.service.EmployeeService;
import jakarta.validation.Valid;
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
    public Employee createEmployee(@Valid @RequestBody Employee employee){
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

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> existingEmployee = employeeService.getEmployee(id);
        if (existingEmployee.isPresent()) {
            Employee updatedEmployee = existingEmployee.get();
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setMail(employee.getMail());
            return employeeService.updateEmployee(updatedEmployee);
        } else {
            // Handle the case when the employee with the given ID does not exist
            // You can throw an exception or return a specific response
            return null; // Placeholder, you can customize this behavior
        }
    }

    @PatchMapping("/employee/{id}")
    public Employee partialUpdate(@PathVariable Long id, @RequestBody Employee employee) {
        //Optional<Employee> existingEmployee = employeeService.getEmployee(id);
        return employeeService.partialUpdate(employee, id);
    }
}


