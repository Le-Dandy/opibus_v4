package ch.opibus.opibus.partner.crud;

import ch.opibus.opibus.partner.dao.PartnerSettings;
import ch.opibus.opibus.partner.dao.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface PartnerSettingsRep extends JpaRepository<PartnerSettings, Long> {

    @Query("select p from PartnerSettings p where p.partner = ?1")
    Optional<PartnerSettings> findByPartner(Partner partner);
}
