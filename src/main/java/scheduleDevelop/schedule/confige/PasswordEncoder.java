package scheduleDevelop.schedule.confige;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    // encode : 사용자가 입력한 비밀번호를 '암호화' 해서 반환 (user 서비스 로그인)
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    // matches : 로그인 시 사용자가 입력한 비밀번호를 가져오고, 'user password 랑 일치 판단' (user 서비스 회원가입 / schedule 서비스)
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}