package ch.opibus.opibus.security.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "validation_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationToken {

    @Id
    @SequenceGenerator(name = "validation_token_sequence", sequenceName = "validation_token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "validation_token_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    private long appUserId;

    private String validationKey;

    private LocalDateTime validTo;
}
