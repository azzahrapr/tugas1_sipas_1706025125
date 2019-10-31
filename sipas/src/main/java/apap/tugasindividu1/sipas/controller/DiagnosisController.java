package apap.tugasindividu1.sipas.controller;

import apap.tugasindividu1.sipas.model.DiagnosisPenyakitModel;
import apap.tugasindividu1.sipas.model.PasienDiagnosisModel;
import apap.tugasindividu1.sipas.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DiagnosisController {

    @Qualifier("diagnosisServiceImpl")

    @Autowired
    private DiagnosisService diagnosisService;

    @RequestMapping(value = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String viewAllDiagnosis(Model model){
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisService.getAllDiagnosisList();
        model.addAttribute("listDiagnosis", listDiagnosis);
        return "view-all-diagnosis";
    }

    @RequestMapping(value = "/diagnosis-penyakit", method = RequestMethod.GET)
    public String viewDiagnosis(@RequestParam(value = "idDiagnosis") Long id, Model model){
        DiagnosisPenyakitModel diagnosis = diagnosisService.getDiagnosisById(id);
        model.addAttribute("diagnosis", diagnosis);
        return "view-diagnosis";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.GET)
    public String addDiagnosisForm(Model model){
        DiagnosisPenyakitModel newDiagnosis = new DiagnosisPenyakitModel();
        model.addAttribute("newDiagnosis", newDiagnosis);
        return "form-add-diagnosis";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.POST)
    public String addDiagnosisSubmit(@ModelAttribute DiagnosisPenyakitModel newDiagnosis, Model model){
        diagnosisService.addDiagnosis(newDiagnosis);
        model.addAttribute("newDiagnosis", newDiagnosis);
        return "add-diagnosis";
    }

    @RequestMapping(value="diagnosis-penyakit/hapus/{idDiagnosis}", method = RequestMethod.POST)
    public String deletePenyakit(@PathVariable Long idDiagnosis , Model model) {
        DiagnosisPenyakitModel diagnosis = diagnosisService.getDiagnosisById(idDiagnosis);
        List<PasienDiagnosisModel> pasienList = diagnosis.getPasienDiagnosisPenyakitList();
        String msg = "Penyakit " + diagnosis.getNama() + " tidak dapat dihapus";
        if (pasienList.isEmpty()) {
            String nama = diagnosisService.deleteDiagnosis(diagnosis);
            msg = "Penyakit " + nama + " berhasil dihapus";
        }
        model.addAttribute("msg", msg);
        return "delete-diagnosis";
    }
}
