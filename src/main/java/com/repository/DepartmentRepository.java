package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Department;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "SELECT * FROM Department d WHERE d.Dcode = :Dcode", nativeQuery = true)
    Optional<Department> findByDcode(@Param("Dcode") long Dcode);

    Optional<Department> findByTitle(String title);
}
