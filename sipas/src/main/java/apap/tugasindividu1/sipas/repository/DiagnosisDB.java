package apap.tugasindividu1.sipas.repository;

import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisDB extends JpaRepository<DiagnosisPenyakitModel, Long> {
    DiagnosisPenyakitModel findDiagnosisPenyakitModelById(Long id);
}
