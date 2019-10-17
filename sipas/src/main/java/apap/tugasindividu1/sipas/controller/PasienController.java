package apap.tugasindividu1.sipas.controller;

import apap.tugasindividu1.sipas.model.EmergencyContactModel;
import apap.tugasindividu1.sipas.model.PasienModel;
import apap.tugasindividu1.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")

    @Autowired
    private PasienService pasienService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @DateTimeFormat(pattern = "dd/MM/yy")
    public String home(Model model){
        List<PasienModel> listPasien = pasienService.getPasienList();
        model.addAttribute("listPasien", listPasien);
        return "home";
    }

    @RequestMapping(value = "/pasien/add", method = RequestMethod.GET)
    public String addPasienFormPage(Model model){
        PasienModel newPasien = new PasienModel();
        EmergencyContactModel kontak = new EmergencyContactModel();
        model.addAttribute("kontak", kontak);
        model.addAttribute("pasien", newPasien);
        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/add", method = RequestMethod.POST)
    public String addPasienSubmit(@ModelAttribute PasienModel pasien, Model model){
        pasien.createKode(pasien);
        pasienService.addPasien(pasien);
        model.addAttribute("pasien", pasien);
        return "home";
    }
}

