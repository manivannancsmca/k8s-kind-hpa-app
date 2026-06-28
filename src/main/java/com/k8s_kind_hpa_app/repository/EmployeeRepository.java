package com.k8s_kind_hpa_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.k8s_kind_hpa_app.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
