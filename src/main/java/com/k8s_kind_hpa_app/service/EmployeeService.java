package com.k8s_kind_hpa_app.service;

import java.util.List;

import com.k8s_kind_hpa_app.model.Employee;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee getById(Long id);
    List<Employee> getAll();
    Employee update(Long id, Employee employee);
    void delete(Long id);
}
