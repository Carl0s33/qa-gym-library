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
        LocalDate hoje = LocalDate.of(2026, 6, 20);
        Loan loan = new Loan(book, user, hoje);
        
        double multa = loan.calculateFine(hoje);
        
        assertThat(multa).isEqualTo(0.0);
    }

    @Test
    void deveCalcularMultaQuandoHouverAtraso() {
        LocalDate hoje = LocalDate.of(2026, 6, 20);
        LocalDate dataEmprestimo = hoje.minusDays(20);
        Loan loan = new Loan(book, user, dataEmprestimo);
        
        double multa = loan.calculateFine(hoje);
        
        assertThat(multa).isEqualTo(3.0);
    }
    
    @Test
    void deveTravarMultaNaDataDeDevolucao() {
        LocalDate hoje = LocalDate.of(2026, 6, 20);
        LocalDate dataEmprestimo = hoje.minusDays(20);
        Loan loan = new Loan(book, user, dataEmprestimo);
        
        loan.setReturnDate(hoje); 
        
        double multa = loan.calculateFine(hoje.plusDays(10));

        assertThat(multa).isEqualTo(3.0);
    }
}