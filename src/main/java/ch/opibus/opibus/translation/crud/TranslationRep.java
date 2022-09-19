package ch.opibus.opibus.translation.crud;

import ch.opibus.opibus.translation.dao.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface TranslationRep extends JpaRepository <Translation, Long> {

    @Query("select t from Translation t " +
            "where t.object.objectName = ?1 and t.field.fieldName = ?2 and t.type.typeName = ?3 and t.languageKey = ?4")
    Optional<Translation> findByObject_ObjectNameAndField_FieldNameAndType_TypeNameAndLanguageKey(String objectName, String fieldName, String typeName, String languageKey);

}
