package com.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.form.entity.EnrollStudent;


@Repository
public interface User2Repository extends JpaRepository<EnrollStudent, Long> {
	
}

