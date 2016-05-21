package com.math040.gambling.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.math040.gambling.GamblingException;

@Controller
public class BaseController {
	private static Logger logger = LoggerFactory.getLogger(BaseController.class); 
@ExceptionHandler
public String exp(HttpServletRequest request, Exception ex) {  
    logger.error(ex.toString());
    ex.printStackTrace();
    request.setAttribute("ex", ex);  
       
    if(ex instanceof GamblingException) {  
        return "error-gamblingException";  
    } else {  
        return "error";  
    }  
}  
}
