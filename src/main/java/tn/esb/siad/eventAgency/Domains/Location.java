package tn.esb.siad.eventAgency.Domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"description", "id"})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int streetNumber;
    private String streetName;
    private String city;
    private String country;
    private int zipCode;
    private String description;
    //implement the relationship between Event and Location (1-*)
    @OneToMany(mappedBy = "eventLocation")
    private Set<Event> events=new HashSet<>();

}
