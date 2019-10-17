package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.PasienModel;
import apap.tugasindividu1.sipas.repository.PasienDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService {

    @Autowired
    PasienDB pasienDB;

    @Override
    public void addPasien(PasienModel pasienModel) {
        pasienDB.save(pasienModel);
    }

    @Override
    public List<PasienModel> getPasienList(){
        return pasienDB.findAll();
    }
}
