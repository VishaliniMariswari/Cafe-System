package com.vishalini.cafe.JWT;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {
	
	private String secret="MyFirstFullStackProject001";
	
	public <T> T extractClaims(String token, Function<Claims,T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
		
	}
	
	
	public Claims extractAllClaims(String Token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(Token).getBody();
	}
	
	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public boolean validateTokenByUserNameExpiration(String token, UserDetails userDetails) {
		return (extractUserName(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}
	
	public String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*5))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
					
	}
	
	public String generateToken(String username,String role) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("role",role);
		return createToken(claims, username);
	}

}
