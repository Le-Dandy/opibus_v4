package ch.opibus.opibus.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityPermission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    USER_READ("admin:read"),
    USER_WRITE("admin:write");


    private final String permission;

}
