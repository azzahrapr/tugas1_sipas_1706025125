package apap.tugasindividu1.sipas.controller;

import apap.tugasindividu1.sipas.model.*;
import apap.tugasindividu1.sipas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")

    @Autowired
    private PasienService pasienService;

    @Autowired
    private AsuransiService asuransiService;

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private EmergencyContactService emergencyContactService;

    @Autowired
    private PasienDiagnosisService pasienDiagnosisService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        List<PasienModel> listPasien = pasienService.getPasienList();
        model.addAttribute("listPasien", listPasien);
        return "home";
    }

    @RequestMapping(value="/pasien/add", method = RequestMethod.POST, params= {"addRow"})
    public String addRowAsuransi(@ModelAttribute PasienModel pasienModel, BindingResult bindingResult, Model model) {
        if (pasienModel.getListAsuransi() == null) {
            pasienModel.setListAsuransi(new ArrayList<AsuransiModel>());
        }
        pasienModel.getListAsuransi().add(new AsuransiModel());
        model.addAttribute("pasien", pasienModel);
        List<AsuransiModel> asuransiModel = asuransiService.getAsuransiList();
        model.addAttribute("listAsuransi", asuransiModel);
        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/add", method = RequestMethod.GET)
    public String addPasienFormPage(Model model) {
        PasienModel pasien = new PasienModel();
        AsuransiModel asuransi = new AsuransiModel();
        List<AsuransiModel> listAsuransi = new ArrayList<AsuransiModel>();
        listAsuransi.add(asuransi);
        pasien.setListAsuransi(listAsuransi);

        List<AsuransiModel> listAllAsuransi = asuransiService.getAsuransiList();
        EmergencyContactModel kontak = new EmergencyContactModel();
        model.addAttribute("listAsuransi", listAllAsuransi);
        model.addAttribute("pasien", pasien);
        model.addAttribute("contact", kontak);
        return "form-add-pasien";
    }

    @RequestMapping(value = "/pasien/add", method = RequestMethod.POST)
    public String addPasienSubmit (@ModelAttribute PasienModel pasien, Model model) {
        pasien.createKode(pasien);
        pasienService.addPasien(pasien);
        model.addAttribute("namaPasien", pasien.getNama());
        model.addAttribute ("kodePasien", pasien.getKode());
        return "add-pasien";
    }

    @RequestMapping(value = "/pasien", method = RequestMethod.GET)
    public String viewPasien(@RequestParam(value = "nik") String nik, Model model){
        PasienModel pasien = pasienService.getPasienByNik(nik);
        List<AsuransiModel> listAsuransi = pasien.getListAsuransi();
        model.addAttribute("pasien", pasien);
        model.addAttribute("listAsuransi", listAsuransi);
        return "view-pasien";
    }

    @RequestMapping(value = "pasien/ubah/{nik}", method = RequestMethod.GET)
    public String ubahPasienFormPage(@PathVariable String nik, Model model){
        PasienModel existingPasien = pasienService.getPasienByNik(nik);
        model.addAttribute("pasien", existingPasien);
        return "form-ubah-pasien";
    }

    //API yang digunakan untuk submit form change menu
    @RequestMapping(value = "/pasien/ubah/{nik}", method = RequestMethod.POST)
    public String ubahPasienFormSubmit(@PathVariable String nik, @ModelAttribute PasienModel pasien, Model model){
        EmergencyContactModel emergencyContact = emergencyContactService.changeEmergencyContact(pasien.getId_emergency_contact());
        pasien.setId_emergency_contact(emergencyContact);
        PasienModel newPasienData = pasienService.changePasien(pasien);
        model.addAttribute("namaPasien", newPasienData.getNama());
        model.addAttribute("kodePasien", newPasienData.getKode());
        return "ubah-pasien";
    }

    @RequestMapping(value = "/pasien/{nik}/tambah-diagnosis", method = RequestMethod.GET)
    public String tambahDiagnosisFormPage(@PathVariable String nik, Model model){
        PasienModel pasien = pasienService.getPasienByNik(nik);
        PasienDiagnosisModel pasienDiagnosis = new PasienDiagnosisModel();
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisService.getAllDiagnosisList();
        List<PasienDiagnosisModel> listPasienDiagnosis = pasien.getPasienDiagnosisList();
        model.addAttribute("pasienDiagnosis", pasienDiagnosis);
        model.addAttribute("listDiagnosis", listDiagnosis);
        model.addAttribute("pasien", pasien);
        model.addAttribute("listPasienDiagnosis", listPasienDiagnosis);
        return "form-add-pasien-diagnosis";
    }


    @RequestMapping(value = "/pasien/{nik}/tambah-diagnosis", method = RequestMethod.POST)
    public String tambahDiagnosisSubmitPage(@PathVariable String nik, @ModelAttribute DiagnosisPenyakitModel diagnosisPenyakitModel,
                                            Model model){
        PasienDiagnosisModel pasienDiagnosis = new PasienDiagnosisModel();
        PasienModel pasienModel = pasienService.getPasienByNik(nik);
        pasienDiagnosis.setPasienModel(pasienModel);
        pasienDiagnosis.setTgl_diagnosis(Date.valueOf(LocalDate.now()));
        pasienDiagnosis.setDiagnosisPenyakitModel(diagnosisPenyakitModel);
        pasienDiagnosisService.addPasienDiagnosis(pasienDiagnosis);
        model.addAttribute("namaPasien", pasienModel.getNama());
        model.addAttribute("tglDiagnosis", pasienDiagnosis.getTgl_diagnosis());
        model.addAttribute("namaDiagnosis", diagnosisPenyakitModel.getNama());
        return "add-pasien-diagnosis";
    }

//    @RequestMapping(value = "/pasien/cari",method = RequestMethod.GET)
//    public String cariPage (Model model){
//        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
//        List<DiagnosisPenyakitModel> listPenyakit = diagnosisService.getAllDiagnosisList();
//        model.addAttribute("listAsuransi",listAsuransi);
//        model.addAttribute("listPenyakit",listPenyakit);
//        return "form-cari-pasien";
//    }
//
//
//    @RequestMapping(value ="/pasien/cari",method = RequestMethod.GET,params = { "idAsuransi","idDiagnosis"})
//    public String cariAsuransiPenyakit (
//            @RequestParam (value = "idAsuransi") Long idAsuransi,
//            @RequestParam(value = "idDiagnosis") Long idDiagnosis, Model model){
//        AsuransiModel asuransi = asuransiService.getAsuransiById(idAsuransi);
//        DiagnosisPenyakitModel diagnosis = diagnosisService.getDiagnosisById(idDiagnosis).get();
//
//        List<PasienDiagnosisModel> listPenyakitPasien = pasienDiagnosisService.getPasienDiagnosisByDiagnosis(diagnosis);
//        List<PasienModel> listPasien = asuransi.getPasienList();
//        List<PasienModel> targetPasien = new ArrayList<>();
//
//        for(PasienDiagnosisModel penyakitPasien : listPenyakitPasien){
//            for(PasienModel pasien : listPasien){
//                if(pasien.getId() == penyakitPasien.getPasienModel().getId()){
//                    targetPasien.add(pasien);
//                }
//            }
//        }
//        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
//        List<DiagnosisPenyakitModel> listPenyakit = diagnosisService.getAllDiagnosisList();
//
//        model.addAttribute("pasien",listPasien);
//        model.addAttribute("listPasienDicari",targetPasien);
//        model.addAttribute("listAsuransi",listAsuransi);
//        model.addAttribute("listPenyakit",listPenyakit);
//          return "cari-pasien";
//    }

    @RequestMapping(path = "/pasien/cari" , method = RequestMethod.GET)
    public String cariPasienSubmit(
                                   @RequestParam(value = "idDiagnosis", required = false) Long idDiagnosis, Model model){

        List<PasienDiagnosisModel> listPasien;
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisService.getAllDiagnosisList();
        model.addAttribute("listDiagnosis", listDiagnosis);

        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
        model.addAttribute("listAsuransi", listAsuransi);

        if (idDiagnosis == 0) {
            return "form-cari-pasien";
        }
        listPasien = pasienDiagnosisService.getPasienByDiagnosis(idDiagnosis);
//        if(idAsuransi == 0 && idDiagnosis== 0){
//            return "form-cari-pasien";
//        }
//        if(idDiagnosis == 0) {
//            listPasien = pasienDiagnosisService.getPasienByAsuransi(idAsuransi);
//        }else if (idAsuransi == 0){
//            listPasien= pasienDiagnosisService.getPasienByDiagnosis(idDiagnosis);
//        }
//        else {
//            listPasien = pasienDiagnosisService.getPasienByAsuransiAndDiagnosis(idAsuransi, idDiagnosis);
//        }
        model.addAttribute("listPasien", listPasien);
        return "cari-pasien";
    }

    @RequestMapping(path = "/pasien/cari/lakilaki-perempuan" , method = RequestMethod.GET)
    public String jumlahPasienDiagnosis(@RequestParam(value = "idDiagnosis") Long idDiagnosis, Model model) {
        List<DiagnosisPenyakitModel> listDiagnosis = diagnosisService.getAllDiagnosisList();
        model.addAttribute("listDiagnosis", listDiagnosis);

        if (idDiagnosis == 0) {
            return "form-cari-jml-pasien";
        }

        DiagnosisPenyakitModel diagnosisPenyakit = diagnosisService.getDiagnosisById(idDiagnosis);
        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
        Integer jmlLaki = pasienDiagnosisService.getJumlahPasienDiagnosisJenisKelamin(1,idDiagnosis);
        Integer jmlPerempuan = pasienDiagnosisService.getJumlahPasienDiagnosisJenisKelamin(2,idDiagnosis);

        model.addAttribute("jmlLaki", jmlLaki);
        model.addAttribute("jmlPerempuan", jmlPerempuan);

        return "cari-jml-pasien";

    }

}

