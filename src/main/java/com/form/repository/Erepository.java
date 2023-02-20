package com.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.form.entity.Eemployee;

@Repository
public interface Erepository extends JpaRepository<Eemployee, Long>{

}
