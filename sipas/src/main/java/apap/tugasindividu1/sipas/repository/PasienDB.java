package apap.tugasindividu1.sipas.repository;

import apap.tugasindividu1.sipas.model.AsuransiModel;
import apap.tugasindividu1.sipas.model.PasienDiagnosisModel;
import apap.tugasindividu1.sipas.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasienDB extends JpaRepository<PasienModel, Long> {
    List<PasienModel> findPasienModelById(Long id);
    PasienModel findPasienModelByNik(String nik);
    List<PasienModel> findByListAsuransi(AsuransiModel asuransiModel);
    List<PasienModel> findByPasienDiagnosisList(List<PasienDiagnosisModel> pasienDiagnosisModel);
}
