package com.example.tboard.domain.article.member.authentication;

public interface AuthenticationProcessor {
    MemberAuth authenticate(String loginId, String loginPw);
}
