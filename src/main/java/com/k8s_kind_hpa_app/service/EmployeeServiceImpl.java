package com.k8s_kind_hpa_app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k8s_kind_hpa_app.model.Employee;
import com.k8s_kind_hpa_app.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Employee not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee update(Long id, Employee updated) {
        Employee existing = getById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setDepartment(updated.getDepartment());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Employee existing = getById(id);
        repository.delete(existing);
    }

}
