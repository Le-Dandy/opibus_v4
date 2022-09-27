package ch.opibus.opibus.partner.crud;

import ch.opibus.opibus.partner.dao.Partner;
import ch.opibus.opibus.partner.dao.UserHead;
import ch.opibus.opibus.partner.service.PartnerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface PartnerRep extends JpaRepository<Partner, Long> {

    @Query("select p from Partner p where p.appUserId = ?1")
    Optional<Partner> findByAppUserId(long id);

    @Query("select p from Partner p where p.userHead = ?1")
    Optional<Partner> findByUserHead(UserHead userHead);
}
