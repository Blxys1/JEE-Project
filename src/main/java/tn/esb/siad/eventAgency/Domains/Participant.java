package tn.esb.siad.eventAgency.Domains;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Include
    @NonNull
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    //the photo is stored in the database as a byte array
    @Lob
    private byte[] photo;
    //or save the photo on a server's directory and store the path in the database
    //private String photoPath;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    //address_id is the foreign key in the participant table
    //In SQL that annotation is equivalent to : address_id BIGINT references Address(id)
    private Address address;

    //implement the relationship between Participant and Reservation (1-*)
    @OneToMany(mappedBy = "participant")
    private Set<Reservation> reservations=new HashSet<>();

}
