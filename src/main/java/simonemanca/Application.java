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

        // Istanzia i DAO
        UtenteDAOImpl utenteDao = new UtenteDAOImpl(em);
        PrestitoDAOImpl prestitoDao = new PrestitoDAOImpl(em);

        // Avvia una transazione per eseguire operazioni sul database
        em.getTransaction().begin();

        try {
            // Crea dettagli utente
            DettagliUtente dettagliUtente = new DettagliUtente("Indirizzo esempio", "email@example.com", null); // il terzo parametro è null perché verrà impostato dal costruttore di Utente

            // Crea e salva un nuovo Utente con dettagli utente
            Utente nuovoUtente = new Utente("Mario", "Rossi", LocalDate.of(1985, 5, 23), "000123", dettagliUtente);
            utenteDao.salva(nuovoUtente);

            // Crea e salva un nuovo Prestito
            // La logica per collegare un CatalogoItem specifico (Libro o Rivista) andrà implementata qui.
            Prestito nuovoPrestito = new Prestito(nuovoUtente, LocalDate.now(), LocalDate.now().plusDays(30), null); // L'elemento prestato andrà specificato qui.
            prestitoDao.salva(nuovoPrestito);

            // Conferma le operazioni
            em.getTransaction().commit();

            // Visualizza informazioni degli Utenti e dei Prestiti
            System.out.println("Utenti registrati:");
            utenteDao.trovaTutti().forEach(utente -> System.out.println(utente.getNome() + " " + utente.getCognome()));

            System.out.println("Prestiti registrati:");
            prestitoDao.trovaTutti().forEach(prestito -> System.out.println("Prestito a " + prestito.getUtente().getNome() + " fino al " + prestito.getDataRestituzionePrevista()));

        } catch (Exception e) {
            // In caso di errore, annulla le modifiche
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

