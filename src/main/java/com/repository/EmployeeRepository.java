package com.repository;

import com.model.Employee;
import com.model.Employee_type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM Employee e WHERE e.ecode = :Ecode", nativeQuery = true)
    Optional<Employee> findByEcode(@Param("Ecode") long Ecode);

    // get all doctor
    List<Employee> findByEmployeeType(Employee_type employeeType);
}
