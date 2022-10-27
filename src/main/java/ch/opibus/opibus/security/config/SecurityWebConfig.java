package ch.opibus.opibus.security.config;

import ch.opibus.opibus.partner.service.AppUserService;
import ch.opibus.opibus.security.service.AuthFailureHandlerService;
import ch.opibus.opibus.security.service.AuthSuccessHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static ch.opibus.opibus.security.model.SecurityRole.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;

    private final String[] whitelist = {
            "/login",
            "/login/**",
            "/logout",
            "/register",
            "/register/**",
            "/CSS/**"};

    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()

                    .authorizeRequests()
                    .antMatchers(whitelist).permitAll()
                    .antMatchers("/admin/**").hasAnyRole(ADMIN_FULL.name(), ADMIN_BASIC.name())
                    .antMatchers("/user/**").hasAnyRole(USER_FULL.name(), USER_BASIC.name())
                    .anyRequest()
                    .authenticated()

                .and().formLogin()
                    .loginPage("/login").permitAll()
                    .failureHandler(failureHandler)
                    .successHandler(successHandler)
                    .defaultSuccessUrl("/loginSuccess", true)
                .and().rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(30))
                    .key("OpibusSecKey2408")
                    .rememberMeParameter("remember-me")
                .and().logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "XSRF-TOKEN", "remember-me")
                    .logoutSuccessUrl("/login");


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(daoAuthenticationProvider());

    }

    private AuthFailureHandlerService failureHandler;

    private AuthSuccessHandlerService successHandler;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);

        return provider;
    }


}
