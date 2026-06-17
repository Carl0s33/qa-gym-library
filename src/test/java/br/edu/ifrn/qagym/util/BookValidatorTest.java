package br.edu.ifrn.qagym.util;
import br.edu.ifrn.qagym.model.Book;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class BookValidatorTest {

    private final BookValidator validator = new BookValidator();

    @Test
    void deveRetornarFalseQuandoAnoForNegativo() {
        Book book = new Book(
                "123",
                "Código Limpo",
                "Robert Martin",
                -1
        );

        assertFalse(validator.isValid(book));
    }

    @Test
    void deveRetornarFalseQuandoAnoForZero() {
        Book book = new Book(
                "123",
                "Código Limpo",
                "Robert Martin",
                0
        );

        assertFalse(validator.isValid(book));
    }

    @Test
    void deveRetornarFalseQuandoAnoForFuturo() {
        Book book = new Book(
                "123",
                "Código Limpo",
                "Robert Martin",
                Year.now().getValue() + 1
        );

        assertFalse(validator.isValid(book));
    }

    @Test
    void deveRetornarTrueQuandoAnoForValido() {
        Book book = new Book(
                "123",
                "Código Limpo",
                "Robert Martin",
                2020
        );

        assertTrue(validator.isValid(book));

import br.edu.ifrn.qagym.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookValidatorTest {
    private BookValidator validator;

    @BeforeEach
    void setUp() {

        validator = new BookValidator();
    }

    @Test
    void deveRetornarFalsoQuandoAutorForNulo() {

        Book livroAutorNulo = new Book("12345", "O hobbit", null, 1902);

        boolean resultado = validator.isValid(livroAutorNulo);

        assertFalse(resultado, "O validador deveria rejeitar um autor nulo.");
    }

    @Test
    void deveRetornarFalsoQuandoAutorForVazio() {

        Book livroAutorVazio = new Book("12345", "O hobbit", "", 1902);

        boolean resultado = validator.isValid(livroAutorVazio);

        assertFalse(resultado, "O validador deveria rejeitar um autor em branco.");
    }

    @Test
    void deveRetornarVerdadeiroQuandoAutorValido() {

        Book livroComAutor = new Book("12345", "O hobbit", "João Pedro", 1902);


        boolean resultado = validator.isValid(livroComAutor);
        assertTrue(resultado, "O validador deveria aceitar um autor válido.");
    }
}