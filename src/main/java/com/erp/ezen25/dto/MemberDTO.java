package com.erp.ezen25.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class MemberDTO{
        private Long memberId;
        private String userId;
        private String email;
        private String name;
        private Integer percent;

}
