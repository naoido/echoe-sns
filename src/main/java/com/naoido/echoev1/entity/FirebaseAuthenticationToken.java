package com.naoido.echoev1.entity;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {
    private final FirebaseToken token;

    public FirebaseAuthenticationToken(FirebaseToken token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.token = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    public String getUserId() {
        return this.token.getUid();
    }
}
