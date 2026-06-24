package br.edu.ifrn.qagym.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LoanTest {

    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        book = new Book("978-0-13", "Código Limpo", "Robert", 2008);
        user = new User("20241001", "Maria Silva");
    }

    @Test
    void deveRetornarMultaZeroQuandoDevolvidoNoPrazo() {
        Loan loan = new Loan(book, user, LocalDate.now());
        
        double multa = loan.calculateFine(LocalDate.now());
        
        assertThat(multa).isEqualTo(0.0);
    }

    @Test
    void deveCalcularMultaQuandoHouverAtraso() {
        LocalDate dataEmprestimo = LocalDate.now().minusDays(20);
        Loan loan = new Loan(book, user, dataEmprestimo);
        
        double multa = loan.calculateFine(LocalDate.now());
        
        assertThat(multa).isEqualTo(3.0);
    }
    
    @Test
    void deveTravarMultaNaDataDeDevolucao() {
        LocalDate dataEmprestimo = LocalDate.now().minusDays(20);
        Loan loan = new Loan(book, user, dataEmprestimo);
        
        loan.setReturnDate(LocalDate.now()); 
        
        double multa = loan.calculateFine(LocalDate.now().plusDays(10));

        assertThat(multa).isEqualTo(3.0);
    }
}