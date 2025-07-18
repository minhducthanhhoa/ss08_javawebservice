package com.data.sesson08_javawebservice.controller;

import com.data.sesson08_javawebservice.entity.ApiResponse;
import com.data.sesson08_javawebservice.entity.Employee;
import com.data.sesson08_javawebservice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> create(@Valid @RequestBody Employee employee) {
        Employee saved = employeeService.create(employee);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee updated = employeeService.update(id, employee);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee deleted successfully", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAll() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee list fetched", employees));
    }
}
