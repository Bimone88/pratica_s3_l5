package simonemanca.dao;

import simonemanca.entities.Libro;
import java.util.List;

public interface LibroDAO {
    void aggiungiElemento(Libro libro);
    void rimuoviPerISBN(String isbn);
    List<Libro> trovaPerAutore(String autore);
    List<Libro> trovaPerTitolo(String titolo);
    List<Libro> trovaPerAnnoPubblicazione(int anno);
}


