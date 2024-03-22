package simonemanca.dao;

import simonemanca.entities.Prestito;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PrestitoDAOImpl implements PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Prestito trovaPerId(Long id) {
        return em.find(Prestito.class, id);
    }

    @Override
    public List<Prestito> trovaTutti() {
        return em.createQuery("SELECT p FROM Prestito p", Prestito.class).getResultList();
    }

    @Override
    public Prestito salva(Prestito prestito) {
        if (prestito.getId() == null) {
            em.persist(prestito);
        } else {
            prestito = em.merge(prestito);
        }
        return prestito;
    }

    @Override
    public void elimina(Prestito prestito) {
        em.remove(prestito);
    }
}
