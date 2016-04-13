package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages="com.math040.gambling",
excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) }) 
public class TestBasedConfig {

}
