package com.common.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    /* BCrypt : BCryptPasswordEncoder
        spring security 에서 제공하는 비밀번호 단방향 해식 도구
        BCrypt 알고리즘을 사용해 비밀번호 암호화, 자동으로 Salt를 추가해 보안성 향상
        매번 다른 해시값으로 인코딩되어 안전하게 데이터베이스에 저장
        - 단방향 해시 : 비밀번호를 암호화된 해시값으로 변환해 원래 비밀번호로는 복호화할 수 없음
        - Salt 추가 : 비밀번호에 무작위 데이터를 추가 (Salting)해 동일한 비밀번호라해도 다른 해시값으로 변환, 보안성 향상
    */

    // passwordEncoder.encoder(rawPassword)  암호화 (회원가입)
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    // passwordEncoder.matches(rawPassword, hashedPassword)  검증 (로그인)
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
