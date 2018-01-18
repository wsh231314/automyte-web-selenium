package com.auto.myte.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auto.myte.service.UserService;
import com.auto.myte.utils.MD5Utils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 注册UserDetailsService 的bean
	 * @return
	 */
	@Bean
	UserDetailsService userService() {
		return new UserService();
	}

	/* 
	 * user Details Service验证
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService()).passwordEncoder(new PasswordEncoder(){

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Utils.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Utils.encode((String)rawPassword));
            }}); //user Details Service验证
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//
		http.csrf().disable()
		 .authorizeRequests()
         .antMatchers("/bootstrap/**", "/js/**", "/jquery/jquery.min.js", "/user/**").permitAll()
		 .anyRequest().authenticated() //任何请求,登录后可以访问
		 .and()
         .formLogin()
         .loginPage("/login")
         .failureUrl("/login?error")
         .permitAll() //登录页面用户任意访问
         .and()
         .logout().permitAll(); //注销行为任意访问
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth
         .inMemoryAuthentication()
             .withUser("user").password("password").roles("USER");
	}

}
