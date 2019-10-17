package apap.tugasindividu1.sipas.model;

import org.springframework.format.annotation.DateTimeFormat;

import org.apache.commons.lang.RandomStringUtils;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="pasien")
public class PasienModel implements Serializable{

    @Id
    @NotNull
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
    private Date tgl_lahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempat_lahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_kelamin", nullable = false)
    private String jenis_kelamin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_emergency_contact", referencedColumnName = "id")
    private EmergencyContactModel id_emergency_contact;

    @OneToMany(mappedBy = "pasienModel")
    private List<PasienDiagnosisModel> tgl_diagnosis;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "asuransi_pasien",
            joinColumns = { @JoinColumn(name = "id_pasien") },
            inverseJoinColumns = { @JoinColumn(name = "id_asuransi") })
    private List<AsuransiModel> asuransiList;

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

    public Date getTglLahir() {
        return tgl_lahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getJenisKelamin() {
        return jenis_kelamin;
    }

    public void setJenisKelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public EmergencyContactModel getIdEmergencyContact() {
        return id_emergency_contact;
    }

    public void setIdEmergencyContact(EmergencyContactModel idEmergencyContact) {
        this.id_emergency_contact = id_emergency_contact;
    }

    public List<PasienDiagnosisModel> getTglDiagnosis() {
        return tgl_diagnosis;
    }

    public void setTglDiagnosis(List<PasienDiagnosisModel> tglDiagnosis) {
        this.tgl_diagnosis = tgl_diagnosis;
    }

    public List<AsuransiModel> getAsuransiList() {
        return asuransiList;
    }

    public void setAsuransiList(List<AsuransiModel> asuransiList) {
        this.asuransiList = asuransiList;
    }

    public void createKode(PasienModel pasien) {
        if (this.tgl_lahir != pasien.tgl_lahir) {
            Date tanggal_lahir = pasien.getTglLahir();
            String pattern = "dd-MM-yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String strDate = simpleDateFormat.format(tanggal_lahir).replaceAll("-", "");
            String tempKode = (LocalDateTime.now().getYear() + 5) + strDate + pasien.getJenisKelamin() + RandomStringUtils.randomAlphabetic(2).toUpperCase();
            pasien.setKode(tempKode);
        }else {
            Date tanggal_lahir = pasien.getTglLahir();
            String pattern = "dd-MM-yy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String strDate = simpleDateFormat.format(tanggal_lahir).replaceAll("-", "");
            String tempKode = (LocalDateTime.now().getYear() + 5) + strDate + pasien.getJenisKelamin() + RandomStringUtils.randomAlphabetic(3).toUpperCase();
            pasien.setKode(tempKode);
        }
    }
}