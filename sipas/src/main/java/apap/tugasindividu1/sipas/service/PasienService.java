package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.AsuransiModel;
import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import apap.tugasindividu1.sipas.model.PasienDiagnosisModel;
import apap.tugasindividu1.sipas.model.PasienModel;

import java.util.List;

public interface PasienService {
    void addPasien(PasienModel pasienModel);

    List<PasienModel> getPasienList();

    PasienModel getPasienByNik(String nik);

    PasienModel changePasien(PasienModel pasienModel);

    List<PasienModel> getPasienByAsuransi(AsuransiModel asuransiModel);

//    List<PasienModel> getPasienByDiagnosis(List<PasienDiagnosisModel> pasienDiagnosisModel);

}
