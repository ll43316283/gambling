package com.math040.gambling.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.util.StringUtils;

import com.math040.gambling.GamblingException; 
import com.math040.gambling.dto.Title;
import com.math040.gambling.repository.TitleRepository; 
import com.math040.gambling.service.TitleService; 

@Service
public class TitleServiceImpl implements TitleService {
	@Autowired
	TitleRepository titleDao;

	@Override
	public List<Title> findAll()  { 
		return titleDao.findAll();
	}

	@Override
	public Title save(Title title) throws GamblingException{ 
		if(!StringUtils.hasText(title.getTitle()) || !StringUtils.hasText(title.getDescription())){
			throw new GamblingException(GamblingException.TITLE_TITLE_AND_DESC_SHOULD_NOT_BE_NULL);
		} 
		if(!StringUtils.hasText(title.getCode())){
			throw new GamblingException(GamblingException.TITLE_TITLE_CODE_SHOULD_NOT_BE_NULL);
		}
		Title checkCodeExisted = titleDao.findByCode(title.getCode());
		if(checkCodeExisted != null){
			throw new GamblingException(GamblingException.TITLE_TITLE_CODE_SHOULD_BE_UNIQUE);
		}
		return titleDao.save(title);
	}
 

	 
	 
	 
	 
}
