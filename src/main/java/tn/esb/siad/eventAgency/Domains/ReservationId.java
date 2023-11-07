package tn.esb.siad.eventAgency.Domains;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable //this annotation is used to tell the ORM that this class is embedded in another class (Reservation)
//if we remove reservation class, we don't need this class
//the ORM won't create a table for this class
// is created because we have a composite primary key in the reservation table
//Embeddable class have to implement Serializable interface
//because the ORM will store the object of this class in the database
public class ReservationId implements Serializable {
    @Column(name = "participant_id")
    private Long participantId;
    @Column(name = "event_id")
    private Long eventId;
}
