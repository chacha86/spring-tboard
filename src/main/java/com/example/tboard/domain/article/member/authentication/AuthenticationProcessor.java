package com.example.tboard.domain.article.member.authentication;

import com.example.tboard.domain.article.member.authentication.filter.AuthToken;

public interface AuthenticationProcessor {
    MemberAuth authenticate(AuthToken authToken);
}
