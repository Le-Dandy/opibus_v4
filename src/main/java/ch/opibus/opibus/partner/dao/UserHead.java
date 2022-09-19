package ch.opibus.opibus.partner.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_head")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHead {

    @Id
    @SequenceGenerator(name = "user_head_sequence", sequenceName = "user_head_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "user_head_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    private long appUserId;

    private String firstName;

    private String middleName;

    private String LastName;

    private String email;

    private String picture;

    @OneToOne
    private Partner partner;
}
