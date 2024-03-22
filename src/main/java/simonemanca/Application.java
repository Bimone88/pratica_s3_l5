package simonemanca;

import simonemanca.dao.PrestitoDAOImpl;
import simonemanca.dao.UtenteDAOImpl;
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
            // Crea e salva un nuovo Utente
            Utente nuovoUtente = new Utente("Mario", "Rossi", LocalDate.of(1985, 5, 23), "000123");
            utenteDao.salva(nuovoUtente);

            // Crea e salva un nuovo Prestito
            // entità CatalogoItem (Libro o Rivista) salvata nel database da dove prendo l'ID
            Prestito nuovoPrestito = new Prestito(nuovoUtente, LocalDate.now(), LocalDate.now().plusDays(30), null);
            prestitoDao.salva(nuovoPrestito);

            // Conferma le operazioni
            em.getTransaction().commit();

            // Visualizza informazioni degli Utenti
            System.out.println("Utenti registrati:");
            utenteDao.trovaTutti().forEach(utente -> System.out.println(utente.getNome() + " " + utente.getCognome()));

            // Visualizza informazioni dei Prestiti
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
