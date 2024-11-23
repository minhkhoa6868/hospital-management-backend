package com.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Employee_phone;

public interface EmployeePhoneRepository extends JpaRepository<Employee_phone, Long> {
    // find all employee phone number
    @Query(value = "SELECT * FROM Employee_phone ep WHERE ep.Em_code = :Ecode", nativeQuery = true)
    Set<Employee_phone> findAllEmployee_phones(@Param("Ecode") long Ecode);
}
