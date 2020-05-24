package application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import application.jwt.JWTAuthenticationEntryPoint;
import application.jwt.JWTFilter;
import application.security.utils.UserLoader;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${security.admin.password:admin}")
	String adminPassword;
	
	@Autowired JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired JWTFilter jwtFilter;
	@Autowired UserLoader detailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("admin") // preconfigured
			.password(passwordEncoder().encode(adminPassword))
			.roles("ADMIN");
		auth.userDetailsService(detailsService);
	}	

	@Override
	protected void configure(HttpSecurity http)throws Exception{
		
		http.csrf().disable();//allows for spring security 2 running POST requests
		http.httpBasic();//enabling baseAuthentication

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		http.cors();
		
		//http.authorizeRequests().antMatchers("/**").permitAll(); // init backdoor
		
		http.authorizeRequests().antMatchers("/jwt/authenticate").permitAll();		
		http.authorizeRequests().antMatchers("/security/**").hasAnyRole("BOSS","ADMIN");
		http.authorizeRequests().antMatchers("/analist/**").hasAnyRole("BOSS","ANALIST");
		http.authorizeRequests().antMatchers("/manager/**").hasAnyRole("BOSS","MANAGER");
		http.authorizeRequests().antMatchers("/clerk/**").hasAnyRole("BOSS","MANAGER", "CLERK");
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
	  return authenticationManager();
	}
}




