package com.math040.gambling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
 
import com.math040.gambling.dto.UserDto;

@Transactional
public interface UserRepository extends JpaRepository<UserDto, Long> {
	public UserDto findByUserName(String userName);
}
