package com.blog.blogapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService  userDetailsService;
	@Autowired
	private JwtHealper  jwtHealper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestToken = request.getHeader("Authorization");
		System.out.println("request token :"+requestToken);
		//Bearer 323342342345gdgs
		String username = null;
		String token = null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			//System.out.println("jwt healper :" +this.jwtHealper.getUsernameFromToken(token));
			token=requestToken.substring(7);
			System.out.println("token is :" +token);
			try {
			username = this.jwtHealper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("unable to get token");
			}
			catch(ExpiredJwtException e)
			{
				System.out.println("token is expired");
			}
			catch (MalformedJwtException e) {
				// TODO: handle exception
				System.out.println("invalid jwt");
			}
		}
		else
		{
			System.out.println("jwt token does not begin with bearer");
		}
		
		//once we get the token now validate
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwtHealper.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("invalid token");
			}
			
		}
		else
		{
			System.out.println("user name is nulll or contect is not null");
		}
		filterChain.doFilter(request, response);
	}

}
