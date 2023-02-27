package com.springbootrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
	  throws Exception {
	    auth.jdbcAuthentication()
	      .dataSource(ds)
	      .usersByUsernameQuery("select username, pass, enabled FROM users WHERE username=?")
	      .authoritiesByUsernameQuery("select username, role from authority WHERE username=?");
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
      throws Exception {
        httpSecurity.authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .permitAll()
        .and()
        .logout()
        .permitAll();
      return httpSecurity.build();
    }
}
