package com.math040.gambling.service;

import com.math040.gambling.GamblingException;
import com.math040.gambling.dto.Season;

public interface SeasonService {  
	Season getCurrent() throws GamblingException;
}
