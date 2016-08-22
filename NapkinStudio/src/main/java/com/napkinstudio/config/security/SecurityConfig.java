package com.napkinstudio.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import javax.sql.DataSource;

/**
 * Created by User1 on 22.07.2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").authorities("ROLE_USER");
//    }
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
        .antMatchers("/orders").authenticated()
        .and().formLogin().loginPage("/login").defaultSuccessUrl("/orders").failureUrl("/login?error")
        .loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
        .and()
        .logout().logoutSuccessUrl("/")
        .and()
		.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
		.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied")
		;



//        .antMatchers("/orders").authenticated()
//        .and().formLogin().loginPage("/login").defaultSuccessUrl("/orders").failureUrl("/login?error")
//        .and()
//        .logout().logoutSuccessUrl("/");
//
//		.antMatchers("/", "/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
//		.antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')")
//		.antMatchers("/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')")
//		.and().formLogin().loginPage("/login")
//		.loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password").and()
//		.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
//		.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");

        
        
        
    }


    @Autowired
    public void configureGlobal(DataSource dataSource, AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("auth0");
		System.out.println(auth);
		System.out.println("auth1");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login,password,enabled from user where login = ?")
                .authoritiesByUsernameQuery("select user.Login, role.name from user " +
                        "join user_role on user.userId = user_role.users_userId join role on user_role.roles_id = role.id where user.login = ?");
		System.out.println(auth);
		System.out.println("auth2");
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
		System.out.println(auth);
		System.out.println("auth3");
    }

    
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//		auth.authenticationProvider(authenticationProvider());
//	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder;
}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
    
    
    
    
    
}
