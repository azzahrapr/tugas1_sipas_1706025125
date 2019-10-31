package apap.tugasindividu1.sipas.repository;

import apap.tugasindividu1.sipas.model.AsuransiModel;
import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import apap.tugasindividu1.sipas.model.PasienDiagnosisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasienDiagnosisDB extends JpaRepository<PasienDiagnosisModel, Long> {
    List<PasienDiagnosisModel> findPasienDiagnosisModelByDiagnosisPenyakitModel(DiagnosisPenyakitModel diagnosisPenyakit);
    List<PasienDiagnosisModel> findByDiagnosisPenyakitModelIdAndPasienModelJenisKelamin(Long idDiagnosis,Integer n);
    List<PasienDiagnosisModel> findByDiagnosisPenyakitModelId(Long idDiagnosis);
    List<PasienDiagnosisModel> findByPasienModelListAsuransi(Long idAsuransi);
    List<PasienDiagnosisModel> findByPasienModelListAsuransiAndDiagnosisPenyakitModelId(Long idAsuransi, Long idDiagnosis);
}
