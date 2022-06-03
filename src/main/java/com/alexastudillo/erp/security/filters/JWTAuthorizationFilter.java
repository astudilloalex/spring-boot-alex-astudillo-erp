package com.alexastudillo.erp.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alexastudillo.erp.handlers.ResponseHandler;
import com.alexastudillo.erp.security.entities.User;
import com.alexastudillo.erp.security.utilities.JWTTokenUtil;
import com.alexastudillo.erp.security.utilities.SecurityConstants;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final JWTTokenUtil jwtTokenUtil;

	public JWTAuthorizationFilter(final JWTTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws IOException, ServletException {
		final String header = request.getHeader(SecurityConstants.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			responseClient(response,
					new ResponseHandler<Object>().generateResponse("security-token-required", HttpStatus.UNAUTHORIZED));
			return;
		}
		final String token = request.getHeader(SecurityConstants.HEADER_STRING).replace(SecurityConstants.TOKEN_PREFIX,
				"");
		if (!jwtTokenUtil.validate(token)) {
			String message = "invalid-token";
			try {
				jwtTokenUtil.getExpirationDate(token);
			} catch (TokenExpiredException e) {
				message = "token-has-expired";
			}
			responseClient(response, new ResponseHandler<Object>().generateResponse(message, HttpStatus.UNAUTHORIZED));
			return;
		}
		final UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(final String token) {
		final User user = new User();
		try {
			user.setId(Long.parseLong(jwtTokenUtil.getUserId(token)));
			user.setUsername(jwtTokenUtil.getUsername(token));
			if (user.getUsername() == null) {
				return null;
			}
			return new UsernamePasswordAuthenticationToken(user, null, jwtTokenUtil.getAuthorities(token));
		} catch (final NumberFormatException e) {
			return null;
		}
	}

	private void responseClient(final HttpServletResponse response, final ResponseEntity<Object> responseEntity) {
		try {
			response.setStatus(responseEntity.getStatusCodeValue());
			response.setContentType("application/json");
			response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity.getBody()));
			response.getWriter().flush();
		} catch (IOException e) {
		}
	}
}
