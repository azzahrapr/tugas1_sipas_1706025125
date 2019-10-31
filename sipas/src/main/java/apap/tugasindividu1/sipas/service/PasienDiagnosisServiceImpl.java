package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import apap.tugasindividu1.sipas.model.PasienDiagnosisModel;
import apap.tugasindividu1.sipas.repository.PasienDiagnosisDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienDiagnosisServiceImpl implements PasienDiagnosisService {

    @Autowired
    PasienDiagnosisDB pasienDiagnosisDB;

    @Override
    public void addPasienDiagnosis(PasienDiagnosisModel pasienDiagnosisModel){
        pasienDiagnosisDB.save(pasienDiagnosisModel);
    }

    @Override
    public PasienDiagnosisModel getPasienDiagnosisById(Long id){
        return pasienDiagnosisDB.findById(id).get();
    }

    @Override
    public List<PasienDiagnosisModel> getPasienDiagnosisByDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel){
        return pasienDiagnosisDB.findPasienDiagnosisModelByDiagnosisPenyakitModel(diagnosisPenyakitModel);
    }

//    @Override
//    public List<PasienDiagnosisModel> getPasienByAsuransi(Long idAsuransi) {
//        return pasienDiagnosisDB.findByPasienModelListAsuransi(idAsuransi);
//    }

    @Override
    public List<PasienDiagnosisModel> getPasienByDiagnosis(Long idDiagnosis) {
        return pasienDiagnosisDB.findByDiagnosisPenyakitModelId(idDiagnosis);
    }

    @Override
    public List<PasienDiagnosisModel> getPasienByAsuransiAndDiagnosis(Long idAsuransi, Long idDiagnosis) {
        return pasienDiagnosisDB.findByPasienModelListAsuransiAndDiagnosisPenyakitModelId(idAsuransi, idDiagnosis);
    }

    @Override
    public Integer getJumlahPasienDiagnosisJenisKelamin(Integer n, Long idDiagnosis) {
        return pasienDiagnosisDB.findByDiagnosisPenyakitModelIdAndPasienModelJenisKelamin(idDiagnosis,n).size();
    }
}
