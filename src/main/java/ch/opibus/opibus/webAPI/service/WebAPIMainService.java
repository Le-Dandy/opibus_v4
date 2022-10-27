package ch.opibus.opibus.webAPI.service;

import ch.opibus.opibus.partner.dao.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebAPIMainService {

    public Object getAdmin(AppUser thisUser) {

        return new Object();
    }

    public Object getUser(AppUser thisUser) {

        return new Object();

    }
}
