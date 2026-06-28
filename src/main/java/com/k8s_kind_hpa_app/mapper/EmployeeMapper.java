package com.k8s_kind_hpa_app.mapper;

import com.k8s_kind_hpa_app.dto.EmployeeRequest;
import com.k8s_kind_hpa_app.dto.EmployeeResponse;
import com.k8s_kind_hpa_app.model.Employee;

public final class EmployeeMapper {
    private EmployeeMapper() {
    }

    public static Employee toEntity(EmployeeRequest request) {
        return Employee.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).department(request.getDepartment()).build();
    }

    public static EmployeeResponse toResponse(Employee employee) {
        return EmployeeResponse.builder().id(employee.getId()).firstName(employee.getFirstName())
                .lastName(employee.getLastName()).email(employee.getEmail()).department(employee.getDepartment())
                .build();
    }
}
