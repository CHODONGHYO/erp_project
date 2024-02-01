package com.erp.ezen25.entity;

import lombok.Getter;

@Getter
public enum MemberRole {
    BRANCH("ROLE_BRANCH"), ADMIN("ROLE_ADMIN"), PARTNER("ROLE_PARTNER");

    MemberRole(String value) {
        this.value = value;
    }

    private String value;


}
