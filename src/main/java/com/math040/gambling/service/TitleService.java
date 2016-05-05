package com.math040.gambling.service;

import java.util.List;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Title;

public interface TitleService {  
	List<Title> findAll() ;
	
	Title save(Title title) throws GamblingException;
}
