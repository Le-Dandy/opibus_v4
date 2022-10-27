package ch.opibus.opibus.partner.dao;


import ch.opibus.opibus.security.model.SecurityRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "app_user" , uniqueConstraints = {@UniqueConstraint(name = "user_email_unique", columnNames = "email")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AppUser implements UserDetails {

    @Id
    @SequenceGenerator(name = "app_user_sequence", sequenceName = "app_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "app_user_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    private SecurityRole securityRole;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private LocalDateTime lockTime;

    private Boolean accountCredentialsExpired;

    private Boolean enabled;

    private int failedAttempt;



    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !accountCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail(boolean translate) {
        return getMethodName(translate);
    }

    public String getUsername(boolean translate) {
        return getMethodName(translate);
    }

    public String getPassword(boolean translate) {
        return getMethodName(translate);
    }

    private String getMethodName(boolean get) {

        if(get = true){
            return Thread.currentThread().getStackTrace()[2].getMethodName().substring(3);
        } else {
            return null;}


    }
}
