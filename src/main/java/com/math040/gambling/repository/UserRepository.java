package com.math040.gambling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.math040.gambling.vo.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserName(String userName);
}
