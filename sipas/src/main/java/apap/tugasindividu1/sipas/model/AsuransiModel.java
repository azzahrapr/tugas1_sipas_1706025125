package apap.tugasindividu1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="asuransi")
public class AsuransiModel implements Serializable {

    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    public String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis", nullable = false)
    private String jenis;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "asuransiList")
    List<PasienModel> pasienList;

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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public List<PasienModel> getPasienList() {
        return pasienList;
    }

    public void setPasienList(List<PasienModel> pasienList) {
        this.pasienList = pasienList;
    }
}