package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.AsuransiModel;
import apap.tugasindividu1.sipas.repository.AsuransiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsuransiServiceImpl implements AsuransiService {
    @Autowired
    AsuransiDB asuransiDb;

    @Override
    public void addAsuransi(AsuransiModel asuransi) {
        asuransiDb.save(asuransi);

    }

    @Override
    public List<AsuransiModel> getAsuransiList() {
        return asuransiDb.findAll();

    }

    @Override
    public AsuransiModel getAsuransiById(Long id){
        return asuransiDb.findById(id).get();
    }

}
