package sn.pts.comment.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sn.pts.comment.security.services.UserDetailsImpl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static sn.pts.comment.commons.constant.SecurityConstants.*;


@Component
@Slf4j
public class JwtUtils {
    /*
                    .claim("authorities", userPrinciple.getAuthorities())
            .claim("userDetails", userPrinciple.getUserDetails())
            .claim("accountDetails", userPrinciple.getAccountDetails())*/

    public String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith(AUTH_HEADER_BEARER_NAME)) {
            return authHeader.replace(AUTH_HEADER_BEARER_NAME, "");
        }

        return null;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetailsImpl userPrinciple) {
        return generateToken(new HashMap<>(), userPrinciple);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetailsImpl userPrinciple
    ) {
        return buildToken(extraClaims, userPrinciple, WEB_ACCESS_DURATION_MN);
    }

    public String generateRefreshToken(
            UserDetailsImpl userPrinciple
    ) {
        return buildToken(new HashMap<>(), userPrinciple, WEB_REFRESH_DURATION_HOURS);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetailsImpl userPrinciple,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userPrinciple.getUsername())
                .claim("authorities", userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .claim("details", userPrinciple.getDetails())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String buildEmailToken(Map<String, String> infos, long expiration) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .claim("infos", infos)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    //retrieve expiration date from jwt token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //validate token
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
