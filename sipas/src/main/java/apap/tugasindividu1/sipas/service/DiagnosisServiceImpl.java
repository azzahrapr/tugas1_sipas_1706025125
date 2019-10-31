package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import apap.tugasindividu1.sipas.repository.DiagnosisDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    DiagnosisDB diagnosisDB;

    @Override
    public List<DiagnosisPenyakitModel> getAllDiagnosisList() {
        return diagnosisDB.findAll();
    }

    @Override
    public DiagnosisPenyakitModel getDiagnosisById(Long id){
        return diagnosisDB.findById(id).get();
    }

    @Override
    public void addDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel){
        diagnosisDB.save(diagnosisPenyakitModel);
    }

    @Override
    public String deleteDiagnosis(DiagnosisPenyakitModel diagnosisPenyakitModel){
        String nama = diagnosisPenyakitModel.getNama();
        diagnosisDB.delete(diagnosisPenyakitModel);
        return nama;
    }
}
