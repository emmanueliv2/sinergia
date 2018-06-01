package com.santander.crm.sinergia.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component("tokens")
public class Tokens {

//	private static Logger LOGGER = LoggerFactory.getLogger(Tokens.class);
	private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
	private static final byte[] secretBytes = secret.getEncoded();
	private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	public static String generateToken(String idCanal, String subject, Date now, Date exp) {
//		String id = UUID.randomUUID().toString().replace("-", "");
		String id = UUID.randomUUID().toString();
		
		String token = Jwts.builder().setId(id)
				.setSubject(subject)
				.setIssuer(idCanal)
				.setIssuedAt(now)
				.setNotBefore(now)
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes).compact();

		return token;
	}

	public static void verifyToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
		System.out.println("----------------------------");
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
	}

}
