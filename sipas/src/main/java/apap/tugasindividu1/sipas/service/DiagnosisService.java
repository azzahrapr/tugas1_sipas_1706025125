package apap.tugasindividu1.sipas.service;


import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;

import java.util.List;
import java.util.Optional;

public interface DiagnosisService {
    List<DiagnosisPenyakitModel> getAllDiagnosisList();
    DiagnosisPenyakitModel getDiagnosisById(Long id);
    void addDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel);
    String deleteDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel);

}
