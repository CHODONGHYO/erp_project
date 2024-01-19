package com.erp.ezen25.security;

import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.MemberRole;
import com.erp.ezen25.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = this.memberRepository.findByUserId(username);
        if (optionalMember.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
        Member member = optionalMember.get();
        List<GrantedAuthority> auths = new ArrayList<>();

        if("admin".equals(username)) {
            auths.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            auths.add(new SimpleGrantedAuthority(MemberRole.BRANCH.getValue()));
        }
        return new User(member.getUserId(), member.getPassword(), auths);
    }
}
