package ch.opibus.opibus.translation.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tr_object")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationObject {

    @Id
    @SequenceGenerator(name = "tr_object_sequence", sequenceName = "tr_object_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "tr_object_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    private String objectName;

    @OneToMany
    private List<TranslationField> field;

    @OneToMany
    private List<Translation> translation;


}
