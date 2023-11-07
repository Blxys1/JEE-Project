package tn.esb.siad.eventAgency.Domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Reservation {

    @EmbeddedId
    private ReservationId id;
    @Min(1)
    private int nbPlaces;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;
    //implement the relationship between Reservation and Participant (*-1)
    @ManyToOne
    @MapsId("participantId")
    //the @MapsId annotation is used to tell the ORM that the participantId attribute is a foreign key
    //already defined in the ReservationId class
    private Participant participant;
    @ManyToOne
    @MapsId("EventId")
    private Event event;

    //implement the relationship between Reservation and ticket (1-1)
    @OneToOne
    private Ticket ticket;
}
