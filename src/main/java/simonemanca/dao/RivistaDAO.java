package simonemanca.dao;

import simonemanca.entities.Rivista;
import java.util.List;

public interface RivistaDAO {
    void aggiungiElemento(Rivista rivista);
    void rimuoviPerISBN(String isbn);
    List<Rivista> trovaPerAutore(String autore);
    List<Rivista> trovaPerTitolo(String titolo);
    List<Rivista> trovaPerAnnoPubblicazione(int anno);
}

