package ch.opibus.opibus.partner.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "partner")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partner {

    @Id
    @SequenceGenerator(name = "partner_sequence", sequenceName = "partner_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "partner_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    private long appUserId;

    @OneToOne
    private UserHead userHead;

    @OneToOne
    private PartnerSettings settings;


    public String getFirstName(boolean translate) {

        return getMethodName(translate);
    }

    public String getMiddleName(boolean translate) {
        return getMethodName(translate);
    }

    public String getLastName(boolean translate) {
        return getMethodName(translate);
    }

    public String getEmail(boolean translate) {
        return getMethodName(translate);
    }

    private String getMethodName(boolean get) {

        if(get = true){
            return Thread.currentThread().getStackTrace()[2].getMethodName().substring(3);
        } else {
            return null;}


    }
}
