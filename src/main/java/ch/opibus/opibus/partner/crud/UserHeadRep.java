package ch.opibus.opibus.partner.crud;

import ch.opibus.opibus.partner.dao.UserHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserHeadRep extends JpaRepository<UserHead, Long> {

    Optional<UserHead> findByEmail(String email);

    Optional<UserHead> findByAppUserId(long id);
}
