package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Only ADMIN can access /admin/**
                .requestMatchers("/user/**").hasRole("USER")    // Only USER can access /user/**
                .requestMatchers("/", "/signup", "/do_register", "/css/**", "/js/**").permitAll()  // Public pages
                .anyRequest().authenticated()  // All other pages require authentication
            )
            .formLogin(login -> login
                .loginPage("/signin") // Custom login page
                .loginProcessingUrl("/login") // Spring Security handles POST /login
                .defaultSuccessUrl("/user/index", true) // Redirect after successful login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/signin") // Redirect after logout
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Disable CSRF for testing (Enable in production)
        
        return http.build();
    }
}

/* 
package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN can access
																								// /admin/**
				.requestMatchers("/user/**").hasRole("USER") // Only USER can access /user/**
				.requestMatchers("/**").permitAll() // Public access for all other routes
		)
		.formLogin(login -> login
                .loginPage("/signin") // Custom login page
                .loginProcessingUrl("/login") // Handles login POST request
                .defaultSuccessUrl("/user/index", true) // Redirect after successful login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/signin") // Redirect after logout
                .permitAll()
            );
		/*  
		.formLogin(form -> form
		.loginPage("/signin")  // Custom login page
		.loginProcessingUrl("/perform_login")  // Ensure this matches the form action in `login.html`
		.defaultSuccessUrl("/user/index", true)
		.permitAll()
	)
		.logout(logout -> logout.logoutUrl("/logout").permitAll()).csrf(csrf -> csrf.disable());
		*/ // Disable CSRF
																											// if
																											// necessary

//		return http.build();
//	}
//}
/*/

/*@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")  // Only ADMIN can access /admin/**
            .requestMatchers("/user/**").hasRole("USER")    // Only USER can access /user/**
            .requestMatchers("/**").permitAll()            // Public access for all other routes
        )
        .formLogin(form -> form
            .loginPage("/login")  // Custom login page
            .loginProcessingUrl("/perform_login")  // Ensure this matches the form action in `login.html`
            .defaultSuccessUrl("/user/index", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .permitAll()
        )
        .csrf(csrf -> csrf.disable());  // Disable CSRF if necessary (use carefully)

    return http.build();
}
 */