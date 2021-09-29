package com.b101.recruit.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.b101.common.util.JwtTokenUtil;
import com.b101.recruit.domain.entity.User;
import com.b101.recruit.service.impl.UserService;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{

	private UserService userService;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
		super(authenticationManager);
		this.userService = userService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader(JwtTokenUtil.HEADER_STRING);
		if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}

		try {
			Authentication authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception ex) {
			return;
		}

		filterChain.doFilter(request, response);
	}

	@Transactional(readOnly = true)
	public Authentication getAuthentication(HttpServletRequest request) throws Exception {
		String jwtToken = request.getHeader(JwtTokenUtil.HEADER_STRING);

		// 토큰 검증 및 인증 처리 로직
		if (jwtToken != null && StringUtils.hasText(jwtToken)&&jwtToken.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
			JwtTokenUtil.handleError(jwtToken);
			String token = jwtToken.substring(7, jwtToken.length());

			String userEmail = JwtTokenUtil.getUserId(token);
			if (userEmail != null) {
				// 회원 조회
				User user = userService.findByUserEmail(userEmail);
				if (user != null) {
					//인증 정보 생성.
					CustomUserDetails userDetails = new CustomUserDetails(user);
					UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(
							userEmail, null, userDetails.getAuthorities());
					jwtAuthentication.setDetails(userDetails);
					return jwtAuthentication;
				}
			}
			return null;

		return null;
	}

}
