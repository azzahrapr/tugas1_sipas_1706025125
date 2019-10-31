package apap.tugasindividu1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name="emergency_contact")

public class EmergencyContactModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nik", nullable = false)
    private Long nik;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
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

    public Long getNik_contact() {
        return nik;
    }

    public void setNik_contact(Long nik) {
        this.nik = nik;
    }

    public String getNama_contact() {
        return nama;
    }

    public void setNama_contact(String nama) {
        this.nama = nama;
    }

    public Long getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(Long no_hp) {
        this.no_hp = no_hp;
    }

    public PasienModel getPasien() {
        return Pasien;
    }

    public void setPasien(PasienModel pasien) {
        Pasien = pasien;
    }



}
