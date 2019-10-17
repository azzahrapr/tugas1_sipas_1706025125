package apap.tugasindividu1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name="emergency_contact")

public class EmergencyContactModel implements Serializable{

    @Id
    @NotNull
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "nik", nullable = false)
    private Long nik;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
    @Size(max = 14)
    @Column(name ="no_hp", nullable = false)
    private Long no_hp;

    @OneToOne(mappedBy = "id_emergency_contact")
    private PasienModel Pasien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNoHp() {
        return no_hp;
    }

    public void setNoHp(Long noHp) {
        this.no_hp = no_hp;
    }

    public PasienModel getPasien() {
        return Pasien;
    }

    public void setPasien(PasienModel pasien) {
        Pasien = pasien;
    }



}
