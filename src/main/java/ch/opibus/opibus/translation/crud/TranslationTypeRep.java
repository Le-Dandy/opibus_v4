package ch.opibus.opibus.translation.crud;

import ch.opibus.opibus.translation.dao.TranslationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface TranslationTypeRep extends JpaRepository <TranslationType, Long> {

    @Query("select t from TranslationType t where t.typeName = ?1")
    Optional<TranslationType> findByTypeName(String name);
}
