package simonemanca.dao;

import jakarta.persistence.EntityManager;
import simonemanca.entities.CatalogoItem;

public class CatalogoItemDAO {
    private EntityManager entityManager;

    public CatalogoItemDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CatalogoItem trovaPerId(Long id) {
        return entityManager.find(CatalogoItem.class, id);
    }

}

