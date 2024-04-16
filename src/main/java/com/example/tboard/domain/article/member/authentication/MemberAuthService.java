package com.example.tboard.domain.article.member.authentication;

public interface MemberAuthService {
    MemberAuth loadUserByUsername(String loginId);
}
