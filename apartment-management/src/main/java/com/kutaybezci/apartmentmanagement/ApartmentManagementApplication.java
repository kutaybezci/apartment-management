package com.kutaybezci.apartmentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.kutaybezci.apartmentmanagement.convertor.PersonEntityToPerson;
import com.kutaybezci.apartmentmanagement.convertor.PersonToPersonEntity;
import com.kutaybezci.apartmentmanagement.convertor.RoleGroupEntityToRoleGroup;
import com.kutaybezci.apartmentmanagement.convertor.SecurityRoleToSecurityRoleEntity;

@SpringBootApplication
public class ApartmentManagementApplication {
    @Bean
    public MessageSource messageSource() {
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("messages");
	messageSource.setDefaultEncoding("UTF-8");
	return messageSource;
    }

    @Bean
    public ConversionService conversionService() {
	DefaultConversionService service = new DefaultConversionService();
	service.addConverter(new PersonToPersonEntity());
	service.addConverter(new PersonEntityToPerson());
	service.addConverter(new SecurityRoleToSecurityRoleEntity());
	service.addConverter(new RoleGroupEntityToRoleGroup());
	return service;
    }

    public static void main(String[] args) {
	SpringApplication.run(ApartmentManagementApplication.class, args);
    }
}
