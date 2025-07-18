package com.data.sesson08_javawebservice.service;

import com.data.sesson08_javawebservice.advice_controller.ResourceNotFoundException;
import com.data.sesson08_javawebservice.entity.Employee;
import com.data.sesson08_javawebservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        existing.setFullname(updated.getFullname());
        existing.setPhone(updated.getPhone());
        existing.setAddress(updated.getAddress());
        existing.setPosition(updated.getPosition());
        existing.setSalary(updated.getSalary());

        return employeeRepository.save(existing);
    }

    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
