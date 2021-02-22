package com.sample.myproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.myproject.model.EmployerDetails;

public interface EmployerRepository extends JpaRepository<EmployerDetails, Long> {
	EmployerDetails findById(long Id);

}
