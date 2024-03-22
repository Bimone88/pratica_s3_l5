package simonemanca.dao;

import simonemanca.entities.Prestito;
import java.util.List;

public interface PrestitoDAO {
    Prestito trovaPerId(Long id);
    List<Prestito> trovaTutti();
    Prestito salva(Prestito prestito);
    void elimina(Prestito prestito);
}
