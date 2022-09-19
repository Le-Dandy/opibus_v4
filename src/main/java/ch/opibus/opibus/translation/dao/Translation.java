package ch.opibus.opibus.translation.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "translation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Translation {

    @Id
    @SequenceGenerator(name = "translation_sequence", sequenceName = "translation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "translation_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @ManyToOne
    private TranslationObject object;

    @ManyToOne
    private TranslationField field;

    @ManyToOne
    private TranslationType type;

    private String languageKey;

    private String text;

    private String description;

    private String placeholder;

    public Translation(TranslationObject object, TranslationField field, TranslationType type, String languageKey, String text, String description, String placeholder) {
        this.object = object;
        this.field = field;
        this.type = type;
        this.languageKey = languageKey;
        this.text = text;
        this.description = description;
        this.placeholder = placeholder;
    }
}
