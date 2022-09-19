package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.partner.crud.AppUserRep;
import ch.opibus.opibus.partner.dao.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRep dB;


    @Override
    public UserDetails loadUserByUsername(String username) {

        AppUser user = new AppUser();

        try {
            user = getByUsername(username);
            user.setGrantedAuthorities(user.getSecurityRole().getGrantedAuthorities());
        } catch (Error error) {

        }

        return user;
    }

    private AppUser get(AppUser appUser) throws Error{

        try{

            return getByUsername(appUser.getUsername());

        } catch (Error error) {

            return getByUsername(appUser.getEmail());
        }
    }

    public AppUser getByUsername(String username) throws Error{

        try{

            return dB.findByUserName(username).get();

        } catch (Exception e) {


            return dB.findByEmail(username).orElseThrow(() -> new Error("userName", 1));

        }

    }

    private void delete(AppUser appUser) throws Error {

        try{

            dB.delete(appUser);

        } catch(Exception e) {

            throw new Error(appUser, appUser.getId() );
        }
    }

    public AppUser create(AppUser appUser) throws Error{

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        try{

            dB.save(appUser);

        } catch (Exception e) {

            throw new Error(appUser, appUser.getId() );

        }

        return appUser;

    }

    public String getURLPrefixFromUser(String name) {

        try {

            return getByUsername(name).getSecurityRole().getUrlPrefix();

        } catch (Error error) {

            return "";

        }
    }

    public boolean checkIfExists(AppUser appUser) {

        boolean exists = false;

        if(dB.findById(appUser.getId()).isPresent()){

            exists = true;

        } else if(dB.findByEmail(appUser.getEmail()).isPresent()){

            exists = true;

        }

        return exists;

    }


    public void save(AppUser appUser) throws Error {

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        try{

            dB.save(appUser);

        } catch (Exception e) {

            throw new Error(appUser, appUser.getId() );

        }
    }
}
