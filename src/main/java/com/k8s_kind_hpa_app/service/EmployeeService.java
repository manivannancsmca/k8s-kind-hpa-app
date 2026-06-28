package com.k8s_kind_hpa_app.service;

import java.util.List;

import com.k8s_kind_hpa_app.dto.EmployeeRequest;
import com.k8s_kind_hpa_app.dto.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse getById(Long id);

    List<EmployeeResponse> getAll();

    EmployeeResponse update(Long id, EmployeeRequest request);

    void delete(Long id);
}
