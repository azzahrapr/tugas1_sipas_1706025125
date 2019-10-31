package apap.tugasindividu1.sipas.repository;

import apap.tugasindividu1.sipas.model.AsuransiModel;

import apap.tugasindividu1.sipas.model.AsuransiModel;
import apap.tugasindividu1.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsuransiDB extends JpaRepository<AsuransiModel, Long> {
    Optional<AsuransiModel> findById(Long id);
    List<PasienModel> findByPasienList(PasienModel pasienModel);
}
