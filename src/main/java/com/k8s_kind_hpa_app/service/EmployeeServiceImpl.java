package com.k8s_kind_hpa_app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k8s_kind_hpa_app.dto.EmployeeRequest;
import com.k8s_kind_hpa_app.dto.EmployeeResponse;
import com.k8s_kind_hpa_app.exception.DuplicateResourceException;
import com.k8s_kind_hpa_app.exception.ResourceNotFoundException;
import com.k8s_kind_hpa_app.mapper.EmployeeMapper;
import com.k8s_kind_hpa_app.model.Employee;
import com.k8s_kind_hpa_app.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + request.getEmail());
        }
        Employee employee = EmployeeMapper.toEntity(request);
        Employee savedEmployee = repository.save(employee);
        return EmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return EmployeeMapper.toResponse(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAll() {
        return repository.findAll().stream().map(EmployeeMapper::toResponse).toList();
    }

    @Override
    public EmployeeResponse update(Long id, EmployeeRequest request) {

        Employee existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));

        if (!existing.getEmail().equals(request.getEmail())
                && repository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists: " + request.getEmail());
        }

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        Employee updatedEmployee = repository.save(employee);
        return EmployeeMapper.toResponse(updatedEmployee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        repository.delete(employee);
    }

}
