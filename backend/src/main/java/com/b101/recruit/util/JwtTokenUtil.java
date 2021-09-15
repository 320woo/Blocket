package com.b101.recruit.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {

	private static String secretKey;
	private static Integer expirationTime;

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String ISSUER = "b101.com";

	@Autowired
	public JwtTokenUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") Integer expirationTime) {
		this.secretKey = secretKey;
		this.expirationTime = expirationTime;
	}

	public void setExpirationTime() {
		JwtTokenUtil.expirationTime = expirationTime;
	}

	public static String createToken(String userId) {
		Claims claims = Jwts.claims().setSubject(userId);
		Date now = new Date();
		return Jwts.builder().setClaims(claims).setIssuer(ISSUER).setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(new Date(now.getTime() + expirationTime)).signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public static String getUserId(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public static void handleError(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw ex;
		} catch (MalformedJwtException ex) {
			throw ex;
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (UnsupportedJwtException ex) {
			throw ex;
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}

	}

}
