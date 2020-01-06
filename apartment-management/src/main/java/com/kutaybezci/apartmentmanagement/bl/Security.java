package com.kutaybezci.apartmentmanagement.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kutaybezci.apartmentmanagement.bl.impl.UserDetailsServiceImp;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
	return new UserDetailsServiceImp();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests()//
		.antMatchers("/").permitAll()//
		// .antMatchers("/user/create", "/user/get/**",
		// "/user/list").hasAnyRole("ADMIN")
		.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/menu")
		.permitAll().and().logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}
