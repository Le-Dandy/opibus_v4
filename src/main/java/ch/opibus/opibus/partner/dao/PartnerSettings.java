package ch.opibus.opibus.partner.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "settings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerSettings {

    @Id
    @SequenceGenerator(name = "settings_sequence", sequenceName = "settings_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "settings_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    private ColorSettings color;

    @OneToOne
    private Partner partner;

}
