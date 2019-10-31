package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.AsuransiModel;

import java.util.List;
import java.util.Optional;

public interface AsuransiService {
    void addAsuransi(AsuransiModel asuransi);

    List<AsuransiModel> getAsuransiList();

    AsuransiModel getAsuransiById(Long id);
}