package apap.tugasindividu1.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="pasien_diagnosis_penyakit")
public class PasienDiagnosisModel implements Serializable{

    @Id
    @NotNull
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pasien")
    private PasienModel pasienModel;

    @ManyToOne
    @JoinColumn(name = "id_diagnosis")
    private DiagnosisPenyakitModel diagnosisPenyakitModel;

    @NotNull
    @Column(name = "tgl_diagnosis")
    private Date tgl_diagnosis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PasienModel getPasienModel() {
        return pasienModel;
    }

    public void setPasienModel(PasienModel pasienModel) {
        this.pasienModel = pasienModel;
    }

    public DiagnosisPenyakitModel getDiagnosisPenyakitModel() {
        return diagnosisPenyakitModel;
    }

    public void setDiagnosisPenyakitModel(DiagnosisPenyakitModel diagnosisPenyakitModel) {
        this.diagnosisPenyakitModel = diagnosisPenyakitModel;
    }

    public Date getTglDiagnosis() {
        return tgl_diagnosis;
    }

    public void setTglDiagnosis(Date tglDiagnosis) {
        this.tgl_diagnosis = tgl_diagnosis;
    }
}