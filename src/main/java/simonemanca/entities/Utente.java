package simonemanca.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Column(name = "numero_tessera", unique = true)
    private String numeroTessera;

    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private DettagliUtente dettagliUtente;

    public Utente() {
    }

    public Utente(String nome, String cognome, LocalDate dataNascita, String numeroTessera, DettagliUtente dettagliUtente) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroTessera = numeroTessera;
        this.dettagliUtente = dettagliUtente;
        
        if (dettagliUtente != null) {
            dettagliUtente.setUtente(this);
        }
    }

    // Getter e Setter inclusi il nuovo campo dettagliUtente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(String numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public DettagliUtente getDettagliUtente() {
        return dettagliUtente;
    }

    public void setDettagliUtente(DettagliUtente dettagliUtente) {
        this.dettagliUtente = dettagliUtente;
        if (dettagliUtente != null) {
            dettagliUtente.setUtente(this);
        }
    }
}


