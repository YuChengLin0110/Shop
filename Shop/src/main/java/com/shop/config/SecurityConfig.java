package com.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.shop.Service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private MemberService memberService;
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{
//		http
//		.authorizeRequests().anyRequest().permitAll()
//		.and()
//		.formLogin()
//		.loginProcessingUrl("/doLogin")
//		.loginPage("/login")
//		.and()
//		.csrf().disable();
		http
		.authorizeRequests()
		.antMatchers("/login","/register").anonymous()
		.antMatchers("/","/product","/checkAccount","/ecpayCheckOut","/ecpayReturn","/css/**", "/js/**","/fonts/**","/images/**","/sass/**")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/doLogin")
		.defaultSuccessUrl("/")
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.and()
		.csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.ignoringAntMatchers("/ecpayCheckOut","/ecpayReturn");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
		
	}
}
