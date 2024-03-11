package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer>{

}
