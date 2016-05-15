package com.math040.gambling.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.math040.gambling.GamblingException;

@Controller
public class BaseController {
@ExceptionHandler
public String exp(HttpServletRequest request, Exception ex) {  
    
    request.setAttribute("ex", ex);  
       
    if(ex instanceof GamblingException) {  
        return "error-gamblingException";  
    } else {  
        return "error";  
    }  
}  
}
