package tn.esb.siad.eventAgency.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esb.siad.eventAgency.Domains.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
