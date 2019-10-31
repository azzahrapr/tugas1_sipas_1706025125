package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.AsuransiModel;
import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import apap.tugasindividu1.sipas.model.PasienDiagnosisModel;
import apap.tugasindividu1.sipas.model.PasienModel;
import apap.tugasindividu1.sipas.repository.EmergencyContactDB;
import apap.tugasindividu1.sipas.repository.PasienDB;
import apap.tugasindividu1.sipas.repository.PasienDiagnosisDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService {

    @Autowired
    PasienDB pasienDB;

    @Autowired
    EmergencyContactDB emergencyContactDB;

    @Autowired
    PasienDiagnosisDB pasienDiagnosisDB;

    @Override
    public void addPasien(PasienModel pasienModel) {
        pasienDB.save(pasienModel);
    }

    @Override
    public List<PasienModel> getPasienList(){
        return pasienDB.findAll();
    }

    @Override
    public PasienModel getPasienByNik(String nik){
        return pasienDB.findPasienModelByNik(nik);
    }

    @Override
    public PasienModel changePasien(PasienModel pasienModel) {
        PasienModel targetPasien = pasienDB.findById(pasienModel.getId()).get();
        if (pasienModel.getTgl_lahir() != targetPasien.getTgl_lahir()) {
            targetPasien.setTgl_lahir(pasienModel.getTgl_lahir());
            targetPasien.createKode(targetPasien);
        }
        targetPasien.setNama(pasienModel.getNama());
        targetPasien.setNik(pasienModel.getNik());
        targetPasien.setTempat_lahir(pasienModel.getTempat_lahir());
        pasienDB.save(targetPasien);
        return targetPasien;
    }

    @Override
    public List<PasienModel> getPasienByAsuransi(AsuransiModel asuransiModel) {
        return pasienDB.findByListAsuransi(asuransiModel);
    }

//    @Override
//    public List<PasienModel> getPasienByDiagnosis(PasienDiagnosisModel pasienDiagnosisModel) {
//        return pasienDB.findByPasienDiagnosisList(pasienDiagnosisModel);
//    }

}
