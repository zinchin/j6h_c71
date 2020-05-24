package application.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JWTFilter extends OncePerRequestFilter{
	
	@Autowired JWTTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String jwtToken = null;
		Claims claims = null;
		
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			try {
				claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
			}catch(Exception e){
				request.setAttribute("wrong token", e.getMessage());
			}
		}
				
		if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			@SuppressWarnings("unchecked")
			List<Map<String,String>> roles = jwtTokenUtil.getClaimFromToken(jwtToken, klaims -> klaims.get("roles",List.class)); 
			SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(claims.getSubject(),null,
								roles.stream()
								.map(role -> new SimpleGrantedAuthority(role.get("authority")))
								.collect(Collectors.toList()))					
				);								                                			
		}
		
		chain.doFilter(request, response);
	}
}
