package com.repository;

import com.model.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM Employee e WHERE e.Ecode = :Ecode", nativeQuery = true)
    Optional<Employee> findByEcode(@Param("Ecode") long Ecode);
    
    // find all employee
    @Query(value = "SELECT * FROM Employee", nativeQuery = true)
    List<Employee> findAllEmployees();
}
