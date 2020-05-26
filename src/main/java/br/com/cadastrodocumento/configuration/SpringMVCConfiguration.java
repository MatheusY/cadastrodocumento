package br.com.cadastrodocumento.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("br.com.cadastrodocumento")
public class SpringMVCConfiguration {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
