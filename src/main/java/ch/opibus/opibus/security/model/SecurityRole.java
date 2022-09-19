package ch.opibus.opibus.security.model;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ch.opibus.opibus.security.model.SecurityPermission.*;

@Getter
@AllArgsConstructor
public enum SecurityRole {

    ADMIN_FULL(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, USER_READ, USER_WRITE), "admin", true),
    ADMIN_BASIC( Sets.newHashSet(ADMIN_READ, USER_READ), "admin", false),

    USER_FULL( Sets.newHashSet(USER_READ, USER_WRITE), "user", true),
    USER_BASIC( Sets.newHashSet(USER_READ), "user", false);

    private final Set<SecurityPermission> securityPermissions;

    private final String urlPrefix;

    private final boolean payed;

    public Set<SecurityPermission> getSecurityPermissions() {
        return securityPermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        Set<SimpleGrantedAuthority> permissions = getSecurityPermissions().stream()
                .map(securityPermission -> new SimpleGrantedAuthority(securityPermission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }

}
