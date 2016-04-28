package com.math040.gambling.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.dto.Title; 

@Transactional
public interface TitleRepository extends JpaRepository<Title, Long> { 
	Title findByCode(String code);
} 
