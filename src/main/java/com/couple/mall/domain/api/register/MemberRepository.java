package com.couple.mall.domain.api.register;

import com.couple.mall.domain.api.login.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmailAndPassword(LoginRequest request);
}
