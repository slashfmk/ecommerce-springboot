package com.slashfmk.ecommerce.repository;

import com.slashfmk.ecommerce.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository <Department, Long> {
}
