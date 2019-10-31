package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.EmergencyContactModel;
import apap.tugasindividu1.sipas.model.PasienModel;
import apap.tugasindividu1.sipas.repository.EmergencyContactDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService{
    @Autowired
    EmergencyContactDB emergencyContactDB;

    @Override
    public void addEmergencyContact(EmergencyContactModel emergencyContact){
        emergencyContactDB.save(emergencyContact);
    }

    @Override
    public EmergencyContactModel changeEmergencyContact(EmergencyContactModel emergencyContactModel){
        EmergencyContactModel targetContact = emergencyContactDB.findById(emergencyContactModel.getId()).get();
        targetContact.setNama_contact(emergencyContactModel.getNama_contact());
        targetContact.setNik_contact(emergencyContactModel.getNik_contact());
        targetContact.setNo_hp(emergencyContactModel.getNo_hp());
        emergencyContactDB.save(targetContact);
        return targetContact;
    }
}
