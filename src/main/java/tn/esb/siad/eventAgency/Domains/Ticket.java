package tn.esb.siad.eventAgency.Domains;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tn.esb.siad.eventAgency.Enumerations.TicketType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    //this annotation is used to specify the type of the column in the database (VARCHAR)
    private TicketType type;
    @NonNull
    @Column(name = "ticket_price",precision=7,scale=3)
    private BigDecimal price;
    //implement the relationship between Ticket and Reservation (1-1)
    @OneToOne(mappedBy = "ticket",cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Reservation reservation;
}
