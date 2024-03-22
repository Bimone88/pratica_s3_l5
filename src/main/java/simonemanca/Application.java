package simonemanca;

import simonemanca.dao.PrestitoDAOImpl;
import simonemanca.dao.UtenteDAOImpl;
import simonemanca.entities.DettagliUtente;
import simonemanca.entities.Prestito;
import simonemanca.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pratica_s3_l5");
        EntityManager em = emf.createEntityManager();

        try {
            UtenteDAOImpl utenteDao = new UtenteDAOImpl(em);
            PrestitoDAOImpl prestitoDao = new PrestitoDAOImpl(em);

            // Gestisce la transazione all'inizio e alla fine
            em.getTransaction().begin();

            DettagliUtente dettagliUtente = new DettagliUtente("Indirizzo esempio", "email@example.com", null);
            Utente nuovoUtente = new Utente("Mario", "Rossi", LocalDate.of(1985, 5, 23), "000123", dettagliUtente);
            // Imposto l'associazione bidirezionale
            dettagliUtente.setUtente(nuovoUtente);

            // Salvo utente e dettagli utente
            utenteDao.salva(nuovoUtente);

            Prestito nuovoPrestito = new Prestito(nuovoUtente, LocalDate.now(), LocalDate.now().plusDays(30), null);
            prestitoDao.salva(nuovoPrestito);

            em.getTransaction().commit();

            System.out.println("Operazioni completate con successo.");
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}

