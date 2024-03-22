package simonemanca.dao;

import simonemanca.entities.Rivista;
import jakarta.persistence.EntityManager;
import java.util.List;

public class RivistaDAOImpl implements RivistaDAO {
    private final EntityManager em;

    public RivistaDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void aggiungiElemento(Rivista rivista) {
        em.persist(rivista);
    }

    @Override
    public void rimuoviPerISBN(String isbn) {
        Rivista rivista = em.find(Rivista.class, isbn);
        if (rivista != null) {
            em.remove(rivista);
        }
    }

    @Override
    public List<Rivista> trovaPerAutore(String autore) {
        return em.createQuery("SELECT r FROM Rivista r WHERE r.autore = :autore", Rivista.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    @Override
    public List<Rivista> trovaPerTitolo(String titolo) {
        return em.createQuery("SELECT r FROM Rivista r WHERE r.titolo LIKE CONCAT('%', :titolo, '%')", Rivista.class)
                .setParameter("titolo", titolo)
                .getResultList();
    }

    @Override
    public List<Rivista> trovaPerAnnoPubblicazione(int anno) {
        return em.createQuery("SELECT r FROM Rivista r WHERE r.annoPubblicazione = :anno", Rivista.class)
                .setParameter("anno", anno)
                .getResultList();
    }
}
