package com.trendflow.member.global.security;

import com.trendflow.member.member.entity.Member;
import com.trendflow.member.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetail implements UserDetails {
    private String memberId;
    private String password;
    private List<String> roles = new ArrayList<>();

    public static UserDetails of (Member member){
        return CustomUserDetail.builder()
                .memberId(String.valueOf(member.getMemberId()))
                .password(member.getPassword())
                .roles(member.getRoles().stream()
                        .map(Role::getRoleCode)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
