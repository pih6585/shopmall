package com.study.shopmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.study.shopmall.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final MemberService memberService;

	public SecurityConfig(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/")
			.usernameParameter("email")
			.failureUrl("/members/login/error")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("members/logout"))
			.logoutSuccessUrl("/");

		http.authorizeRequests()
			.mvcMatchers("/", "members/**",
				"/item/**", "/images/**").permitAll()
			.mvcMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated();

		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService)
			.passwordEncoder(passwordEncoder());
	}

	@SuppressWarnings("RedundantThrows")
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
}
