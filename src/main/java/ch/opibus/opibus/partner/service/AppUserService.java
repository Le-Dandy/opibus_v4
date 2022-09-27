package ch.opibus.opibus.partner.service;

import ch.opibus.opibus.error.model.DBError;
import ch.opibus.opibus.error.model.Error;
import ch.opibus.opibus.partner.crud.AppUserRep;
import ch.opibus.opibus.partner.dao.AppUser;
import ch.opibus.opibus.partner.dao.Partner;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRep dB;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         try {

            AppUser user = get(username);
            user.setGrantedAuthorities(user.getSecurityRole().getGrantedAuthorities());

             return user;

        } catch (DBError error) {

             throw  new UsernameNotFoundException(error.getMessage());
        }


    }


    public AppUser get(AppUser appUser) throws DBError{

        try{

            return get(appUser.getUsername());

        } catch (DBError userNameError) {

            try{

                return get(appUser.getEmail());

            } catch (DBError emailError){

                throw new DBError(appUser, appUser.getId());

            }


        }
    }

    public AppUser get(String userName) throws DBError {

        try{

            return dB.findByUsername(userName).get();

        } catch (Exception e) {

            try {

                return dB.findByEmail(userName).get();

            } catch (Exception error) {

                throw new DBError(new AppUser());

            }

        }

    }

    private void delete(AppUser appUser) throws DBError {

        if(appUser.getId() < 0){

            try{

                dB.delete(appUser);

            } catch(Exception e) {

                throw new DBError(appUser, appUser.getId() );
            }

        }


    }

    public AppUser create(AppUser appUser) throws DBError{

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        try{

            dB.save(appUser);

        } catch (Exception e) {

            throw new DBError(appUser, appUser.getId() );

        }

        return appUser;

    }

    public String getURLPrefixFromUser(String name) throws DBError{

        try {

            return get(name).getSecurityRole().getUrlPrefix();

        } catch (DBError error) {

            throw error;

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

    public void save(AppUser appUser) throws DBError {

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        try{

            dB.save(appUser);

        } catch (Exception e) {

            throw new DBError(appUser, appUser.getId() );

        }
    }

    public void delete(long appUserId) throws DBError {

        try{

            dB.delete(dB.getById(appUserId));

        } catch (Exception e){

            throw new DBError(new AppUser(), appUserId);

        }
    }

    public AppUser getByEmail(String email) throws DBError {

        try {

            return dB.findByEmail(email).get();

        } catch (Exception error) {

            throw new DBError(new AppUser());

        }
    }
}
