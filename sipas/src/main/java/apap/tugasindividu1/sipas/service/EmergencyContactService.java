package apap.tugasindividu1.sipas.service;

import apap.tugasindividu1.sipas.model.EmergencyContactModel;

public interface EmergencyContactService {
    void addEmergencyContact(EmergencyContactModel emergencyContact);

    EmergencyContactModel changeEmergencyContact(EmergencyContactModel emergencyContactModel);
}
