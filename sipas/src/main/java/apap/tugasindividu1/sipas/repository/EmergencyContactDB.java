package apap.tugasindividu1.sipas.repository;

import apap.tugasindividu1.sipas.model.EmergencyContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyContactDB extends JpaRepository<EmergencyContactModel, Long> {
}
