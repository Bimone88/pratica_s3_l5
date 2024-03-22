package simonemanca.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dettagli_utente")
public class DettagliUtente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String indirizzo;

    @Column(nullable = false)
    private String email; // Aggiungi altri campi se necessario

    @OneToOne
    @JoinColumn(name = "utente_id", unique = true, nullable = false)
    private Utente utente;

    // Costruttori
    public DettagliUtente() {}

    public DettagliUtente(String indirizzo, String email, Utente utente) {
        this.indirizzo = indirizzo;
        this.email = email;
        this.utente = utente;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
