package ch.opibus.opibus.partner.crud;

import ch.opibus.opibus.partner.dao.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Repository
public interface AppUserRep extends JpaRepository<AppUser, Long> {

    Optional<AppUser>findByUsername(String userName);

    @Query("select a from AppUser a where a.email = ?1")
    Optional<AppUser> findByEmail(String email);

    @Query("select a from AppUser a where a.email = ?1 and a.password = ?2")
    Optional<AppUser> findByEmailAndPassword(String email, String password);

}
