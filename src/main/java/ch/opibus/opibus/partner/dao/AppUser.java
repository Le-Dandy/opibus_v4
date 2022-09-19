package ch.opibus.opibus.partner.dao;


import ch.opibus.opibus.error.model.TranslationError;
import ch.opibus.opibus.security.model.SecurityRole;
import ch.opibus.opibus.translation.dao.Translation;
import ch.opibus.opibus.translation.service.TranslationService;
import ch.opibus.opibus.webAPI.model.template.fields.WebTemplateInput;
import ch.opibus.opibus.webAPI.service.template.fields.WebTemplateInputService;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "app_users" , uniqueConstraints = {@UniqueConstraint(name = "user_email_unique", columnNames = "email")})
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
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    private SecurityRole securityRole;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean accountCredentialsExpired;

    private Boolean enabled;

    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return userName;
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

    public String getUserName(boolean translate) {
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
