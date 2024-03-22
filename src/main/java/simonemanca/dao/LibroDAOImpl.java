package simonemanca.dao;

import simonemanca.entities.Libro;
import jakarta.persistence.EntityManager;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {
    private final EntityManager em;

    public LibroDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void aggiungiElemento(Libro libro) {
        em.persist(libro);
    }

    @Override
    public void rimuoviPerISBN(String isbn) {
        Libro libro = em.find(Libro.class, isbn);
        if (libro != null) {
            em.remove(libro);
        }
    }

    @Override
    public List<Libro> trovaPerAutore(String autore) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.autore = :autore", Libro.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    @Override
    public List<Libro> trovaPerTitolo(String titolo) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.titolo LIKE CONCAT('%', :titolo, '%')", Libro.class)
                .setParameter("titolo", titolo)
                .getResultList();
    }

    @Override
    public List<Libro> trovaPerAnnoPubblicazione(int anno) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.annoPubblicazione = :anno", Libro.class)
                .setParameter("anno", anno)
                .getResultList();
    }
}

