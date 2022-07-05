package com.example.demo.Jwtutil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class JwtUtils {
    private String SECRET_KEY="secret";
    public String extractUsername(String tocken){
        return extractClaim(tocken,Claims::getSubject);
    }
    public Date extractExpiration(String tocken){
        return extractClaim(tocken,Claims::getExpiration);
    }
    public <T> T extractClaim(String tocken,Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(tocken);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String tocken){
       // return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(tocken).getBody();
    	return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(tocken).getBody();
    }
    private Boolean isTockenExpired(String  tocken){
        return extractExpiration(tocken).before(new Date());
    }
    public String generateTocken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return createTocken(claims,userDetails.getUsername());
    }
    private String createTocken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }
    public Boolean validateTocken(String tocken, UserDetails userDetails){
        final String username=extractUsername(tocken);
        return (username.equals(userDetails.getUsername())&& !isTockenExpired(tocken));
    }

}
