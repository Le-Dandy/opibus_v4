package ch.opibus.opibus.security.crud;

import ch.opibus.opibus.security.dao.ValidationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ValidationTokenRep extends JpaRepository<ValidationToken, Long> {

}
