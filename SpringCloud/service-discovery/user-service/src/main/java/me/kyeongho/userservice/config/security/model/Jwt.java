package me.kyeongho.userservice.config.security.model;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * JWT를 생성하거나 검증하는 역할을 함.
 * jsonwebtoken 라이브러리를 사용하여 구현됨.
 *
 * @author 유경호 ykh6242@etoos.com
 * @since 2021. 06. 08
 */
@Slf4j
@Data
public class Jwt {

	private final String issuer;
	private final SecretKey clientSecret;
	private final int expirySeconds;
	private final int refreshExpirySeconds;
	private final SignatureAlgorithm signatureAlgorithm;
	private final JwtParser jwtParser;

	public Jwt(final String issuer, final String clientSecret, final int expirySeconds, final int refreshExpirySeconds) {
		this.issuer = issuer;
		this.clientSecret = Keys.hmacShaKeyFor(clientSecret.getBytes(StandardCharsets.UTF_8));
		this.expirySeconds = expirySeconds;
		this.refreshExpirySeconds = refreshExpirySeconds;
		this.signatureAlgorithm = SignatureAlgorithm.HS256;
		this.jwtParser = Jwts.parserBuilder()
			.requireIssuer(issuer)
			.setSigningKey(this.clientSecret)
			.build();
	}

	/**
	 * JWT 토큰을 생성한다.
	 *
	 * @param claims JWT의 payload 부분에 담길 값들을 지닌 Claims 객체
	 * @return 생성된 JWT 토큰이 담긴 String 객체
	 */
	public String create(Claims claims) {

		JwtBuilder builder = Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setIssuer(this.issuer)
			.setIssuedAt(new Date())
			.setSubject(claims.getSub())
				.setExpiration(new Date(System.currentTimeMillis() + (86400L * 1000L)))
		    .claim("userKey", claims.getUserKey())
		    .claim("username", claims.getUsername())
			.claim("userId", claims.getUserId())
			.claim("coCustCd", claims.getCoCustCd())
		    .claim("roles", claims.getRoles())
		    .signWith(clientSecret, signatureAlgorithm);

		return builder.compact();
	}

	/**
	 * JWT 토큰의 검증하고 검증이 완료되었다면 claims를 추출해 반환한다.
	 *
	 * @param token 검증할 JWT 토큰
	 * @return 추출된 header, payload를 담은 Claims 객체
	 */
	public Claims verify(String token) {
		Jws<io.jsonwebtoken.Claims> jws;
		try {
			jws = jwtParser.parseClaimsJws(token);
		} catch (SignatureException e) {
			log.error("Invalid JWT signature", e);
			throw e;
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token", e);
			throw e;
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token");
			throw e;
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token", e);
			throw e;
		} catch (JwtException e) {
			log.error("JWT Exception", e);
			throw e;
		}
		return new Claims(jws.getBody());
	}

	@Data
	@NoArgsConstructor
	public static class Claims {
		private Integer userKey;
		private String username;
		private String userId;
		private Integer coCustCd;
		private String sub;
		private String[] roles;
		private Date iat;
		private Date exp;

		public Claims(io.jsonwebtoken.Claims claims) {
			this.userKey = claims.get("userKey", Integer.class);
			this.username = claims.get("username", String.class);
			this.userId = claims.get("userId", String.class);
			this.coCustCd = claims.get("coCustCd", Integer.class);
			this.sub = claims.get("sub", String.class);
			Object roles = claims.get("roles");
			if (roles instanceof String) {
				this.roles = new String[] { ((String) roles)};
			} else {
				this.roles = (((List<String>) roles)).toArray(new String[0]);
			}
			this.iat = claims.getIssuedAt();
			this.exp = claims.getExpiration();
		}

		public static Claims of(final Integer userKey, final String username, final String userId, final Integer coCustCd, final String sub, final String[] roles) {
			Claims claims = new Claims();
			claims.setUserKey(userKey);
			claims.setUsername(username);
			claims.setUserId(userId);
			claims.setCoCustCd(coCustCd);
			claims.setSub(sub);
			claims.setRoles(roles);
			return claims;
		}

		long getIat() {
			return iat != null ? iat.getTime() : -1;
		}

		long getExp() {
			return exp != null ? exp.getTime() : -1;
		}

		void eraseIat() {
			iat = null;
		}

		void eraseExp() {
			exp = null;
		}
	}
}
