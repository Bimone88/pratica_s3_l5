package simonemanca.dao;

import simonemanca.entities.Utente;
import java.util.List;

public interface UtenteDAO {
    Utente trovaPerId(Long id);
    List<Utente> trovaTutti();
    Utente salva(Utente utente);
    void elimina(Utente utente);
}
