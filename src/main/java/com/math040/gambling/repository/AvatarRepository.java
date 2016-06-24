package com.math040.gambling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.vo.Avatar; 

@Transactional
public interface AvatarRepository extends JpaRepository<Avatar, Long> { 
}
