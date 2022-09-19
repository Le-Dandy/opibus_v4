package ch.opibus.opibus.translation.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tr_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationType {

    @Id
    @SequenceGenerator(name = "tr_type_sequence", sequenceName = "tr_type_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "tr_type_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    private String typeName;

    @OneToMany
    private List<Translation> translation;



}
