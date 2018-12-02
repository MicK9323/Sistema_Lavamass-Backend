package com.mca.apps.lavamassapirest.security;

import com.mca.apps.lavamassapirest.utils.G;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final static Logger LOG = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date expiryDate = new Date(G.newDate().getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Integer.toString(userPrincipal.getId()))
                .setIssuedAt(G.newDate())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public int getUserIdfromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return G.toInteger(claims.getSubject());
    }

    public Boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            LOG.error("Error: Invalid JWT Signature - {}",e.getMessage());
        }catch (MalformedJwtException e){
            LOG.error("Invalid JWT token - {}",e.getMessage());
        }catch (ExpiredJwtException e){
            LOG.error("Expired JWT token - {}",e.getMessage());
        }catch (UnsupportedJwtException e) {
            LOG.error("Unsupported JWT token - {}",e.getMessage());
        }catch (IllegalArgumentException e){
            LOG.error("JWT claims string is empty");
        }
        return false;
    }

}
