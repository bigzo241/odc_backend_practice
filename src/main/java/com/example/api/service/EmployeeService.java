package com.example.api.service;

import com.example.api.model.Employee;
import com.example.api.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeById(final Long id) {
        employeeRepository.deleteById(id);
    }

    public void deleteEmployees() {
        employeeRepository.deleteAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee partialUpdate(Employee employee, Long id) {
        Employee employee1 = employeeRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Utilisateur introuvable"));
        if (employee.getFirstName() != null){
            employee1.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null){
            employee1.setLastName(employee.getLastName());
        }
        if (employee.getMail() != null){
            employee1.setMail(employee.getMail());
        }
        if (employee.getPassword() != null){
            employee1.setPassword(employee.getPassword());
        }

        return employeeRepository.save(employee1);
    }
}
