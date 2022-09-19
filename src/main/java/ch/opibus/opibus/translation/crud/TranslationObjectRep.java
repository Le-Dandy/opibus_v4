package ch.opibus.opibus.translation.crud;

import ch.opibus.opibus.translation.dao.TranslationObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface TranslationObjectRep extends JpaRepository <TranslationObject, Long> {

    @Query("select t from TranslationObject t where t.objectName = ?1")
    Optional<TranslationObject> findByObjectName(String objectName);
}
