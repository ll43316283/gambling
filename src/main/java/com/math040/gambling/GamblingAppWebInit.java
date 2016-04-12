package com.math040.gambling;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.math040.gambling.config.*;

public class GamblingAppWebInit extends AbstractAnnotationConfigDispatcherServletInitializer  {
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class,JpaConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
