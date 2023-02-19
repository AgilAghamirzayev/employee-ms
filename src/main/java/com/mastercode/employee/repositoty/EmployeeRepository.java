package com.mastercode.employee.repositoty;

import com.mastercode.employee.entity.Employee;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    PageImpl<Employee> findByNameContainsOrSurnameContains(String name, String surname, Pageable pageable);

}
