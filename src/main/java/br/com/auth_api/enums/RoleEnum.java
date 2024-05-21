package br.com.auth_api.enums;

public enum RoleEnum {

    ADMIN(1L),
    BASIC(2L);

    private Long roleId;

    RoleEnum(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
