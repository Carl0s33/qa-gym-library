package br.edu.ifrn.qagym.util;

import br.edu.ifrn.qagym.model.Book;
import java.time.Year;
public class BookValidator {

    public boolean isValid(Book book) {
        if (book == null) {
            return false;
        }
        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            return false;
        }
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            return false;
        }
        // TODO: validar autor vazio
        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            return false;
        }
        // TODO: validar ano de publicação inválido (ex: ano futuro ou negativo)
        if(book.getPublicationYear() <= 0 || book.getPublicationYear() > Year.now().getValue()){
            return false;
        }
        return true;
    }
}
