package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.PasienModel;

import java.util.List;

public interface PasienService {
    void addPasien(PasienModel pasienModel);

    List<PasienModel> getPasienList();

}
