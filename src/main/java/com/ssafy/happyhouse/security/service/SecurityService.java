package com.ssafy.happyhouse.security.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    private static final String SECRET_KEY = "tfdsafsadgfwtgewgwegweriojhweqjiprwbagsfgukssgroirgoijesyylnsodgoifdsa";

    //로그인 서비스 던질 때 같이하면된다.

    /**
     * JWT 토큰을 만드는 메소드
     *
     * @param subject String member_id 토큰을 만들때 사용한다.
     * @param expTime long 30분: 30*1000*60
     * @return 토큰을 반환한다.
     */
    public String createToken(String subject, long expTime){
        if(expTime<=0){
            throw new RuntimeException("만료시간이 0보다 커야합니다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretkeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretkeyBytes, signatureAlgorithm.getJcaName());

        //아이디는 subject로 사용하고, 비밀번호는 secretkey를 만드는데 사용하는게 어떤가?
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }

    //토큰 검증하는 메서드를 만들어서 boolean type으로 return 해서 토큰을 검증하는 메서드에서 불러서 사용하면 된다.
    //원래는 내부에서 boolean type으로 인증된 토큰이다 아니다 이렇게 만들어줘야한다.

    /**
     * 토큰을 검증하는 메소드
     *
     * @param token String jwt토큰
     * @return boolean t,f
     */
    public boolean validateToken(String token){
        try{
            Jws<Claims> claims  = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    //토큰을 넣어서 풀어준다.
                    .parseClaimsJws(token);
            //풀어줬으니까 값이 나온다. 클레임이 나온다.
            return !claims.getBody()
                    .getExpiration()
                    .before(new Date());
        }catch(Exception e){
            return false;
        }
    }
}
