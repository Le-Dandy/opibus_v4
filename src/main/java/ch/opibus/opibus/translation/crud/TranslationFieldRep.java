package ch.opibus.opibus.translation.crud;

import ch.opibus.opibus.translation.dao.TranslationField;
import ch.opibus.opibus.translation.dao.TranslationObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface TranslationFieldRep extends JpaRepository<TranslationField, Long> {

    @Query("select t from TranslationField t where t.object = ?1 and t.fieldName = ?2")
    Optional<TranslationField> findByObjectAndFieldName(TranslationObject translationObject, String fieldName);

    Optional<TranslationField> findByObject_ObjectNameAndFieldName(String objectName, String fieldName);
}
