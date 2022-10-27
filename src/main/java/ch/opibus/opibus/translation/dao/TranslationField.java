package ch.opibus.opibus.translation.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tr_field")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationField {

    @Id
    @SequenceGenerator(name = "tr_field_sequence", sequenceName = "tr_field_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "tr_field_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    private String fieldName;

    @ManyToOne
    private TranslationObject object;

    @OneToMany
    private List<Translation> translation;

    private String url;


}
