package simonemanca.dao;

import simonemanca.entities.Utente;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UtenteDAOImpl implements UtenteDAO {
    private final EntityManager em;

    public UtenteDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Utente trovaPerId(Long id) {
        return em.find(Utente.class, id);
    }

    @Override
    public List<Utente> trovaTutti() {
        return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
    }

    @Override
    public Utente salva(Utente utente) {
        if (utente.getId() == null) {
            em.persist(utente);
        } else {
            utente = em.merge(utente);
        }
        return utente;
    }

    @Override
    public void elimina(Utente utente) {
        em.remove(em.contains(utente) ? utente : em.merge(utente));
    }
}

