package apap.tugasindividu1.sipas.repository;

import apap.tugasindividu1.sipas.model.PasienModel;
import apap.tugasindividu1.sipas.service.PasienService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasienDB extends JpaRepository<PasienModel, Long> {
    List<PasienModel> findPasienModelById(Long id);
}
