package tn.esb.siad.eventAgency.Domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    //this annotation is used to specify the format of the date when it is serialized to JSON
    private LocalDate foundationDate;
    //implement the relationship between Organizer and Event (*-*)
    @ManyToMany
    @JoinTable(name = "organizer_event",
            joinColumns = @JoinColumn(name = "organizer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events=new HashSet<>();
}
