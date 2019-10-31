package apap.tugasindividu1.sipas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import org.apache.commons.lang.RandomStringUtils;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="pasien")
public class PasienModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name="kode", nullable = false, unique =true)
    private String kode;

    @NotNull
    @Size(max = 255)
    @Column(name="nik", nullable = false)
    private String nik;

    @NotNull
    @Column(name = "tgl_lahir", nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date tgl_lahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempat_lahir;

    @NotNull
    @Column(name = "jenisKelamin", nullable = false)
    private Integer jenisKelamin;

    public Integer getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Integer jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_emergency_contact", referencedColumnName = "id")
    private EmergencyContactModel id_emergency_contact;

    @OneToMany(mappedBy = "pasienModel")
    private List<PasienDiagnosisModel> pasienDiagnosisList;

    public List<AsuransiModel> getListAsuransi() {
        return listAsuransi;
    }

    public void setListAsuransi(List<AsuransiModel> listAsuransi) {
        this.listAsuransi = listAsuransi;
    }

    @ManyToMany
    @JoinTable(name = "asuransi_pasien",
            joinColumns = { @JoinColumn(name = "id_pasien") },
            inverseJoinColumns = { @JoinColumn(name = "id_asuransi") })
    private List<AsuransiModel> listAsuransi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public EmergencyContactModel getId_emergency_contact() {
        return id_emergency_contact;
    }

    public void setId_emergency_contact(EmergencyContactModel id_emergency_contact) {
        this.id_emergency_contact = id_emergency_contact;
    }

    public List<PasienDiagnosisModel> getPasienDiagnosisList() {
        return pasienDiagnosisList;
    }

    public void setPasienDiagnosisList(List<PasienDiagnosisModel> pasienDiagnosisList) {
        this.pasienDiagnosisList = pasienDiagnosisList;
    }


    public void createKode(PasienModel pasien) {
        if (this.tgl_lahir != pasien.tgl_lahir) {
            Date tanggal_lahir = pasien.getTgl_lahir();
            String pattern = "dd-MM-yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String strDate = simpleDateFormat.format(tanggal_lahir).replaceAll("-", "");
            String tempKode = (LocalDateTime.now().getYear() + 5) + strDate + pasien.getJenisKelamin() + RandomStringUtils.randomAlphabetic(2).toUpperCase();
            pasien.setKode(tempKode);
        }else {
            Date tanggal_lahir = pasien.getTgl_lahir();
            String pattern = "dd-MM-yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String strDate = simpleDateFormat.format(tanggal_lahir).replaceAll("-", "");
            String tempKode = (LocalDateTime.now().getYear() + 5) + strDate + pasien.getJenisKelamin() + RandomStringUtils.randomAlphabetic(3).toUpperCase();
            pasien.setKode(tempKode);
        }
    }


}