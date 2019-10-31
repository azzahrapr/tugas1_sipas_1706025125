package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import apap.tugasindividu1.sipas.model.PasienDiagnosisModel;

import java.util.List;
import java.util.Optional;

public interface PasienDiagnosisService {
    void addPasienDiagnosis(PasienDiagnosisModel pasienDiagnosisModel);
    PasienDiagnosisModel getPasienDiagnosisById(Long id);
    List<PasienDiagnosisModel> getPasienDiagnosisByDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel);
    public List<PasienDiagnosisModel> getPasienByAsuransi(Long idAsuransi);
    public List<PasienDiagnosisModel> getPasienByDiagnosis(Long idDiagnosis);
    public List<PasienDiagnosisModel> getPasienByAsuransiAndDiagnosis(Long idAsuransi, Long idDiagnosis);
    Integer getJumlahPasienDiagnosisJenisKelamin(Integer n, Long idDiagnosis);
}
