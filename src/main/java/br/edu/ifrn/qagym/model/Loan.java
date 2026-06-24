package br.edu.ifrn.qagym.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {

    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnDate;

    public Loan(Book book, User user, LocalDate loanDate) {
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.expectedReturnDate = loanDate.plusDays(14);
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public boolean isLate(LocalDate currentDate) {
        // TODO: verificar se o empréstimo está atrasado
        return getReferenceDate(currentDate).isAfter(this.expectedReturnDate);
    }

    public long daysLate(LocalDate currentDate) {
        // TODO: calcular quantidade de dias de atraso
        if (!isLate(currentDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(this.expectedReturnDate, getReferenceDate(currentDate));
    }

    public double calculateFine(LocalDate currentDate) {
        // TODO: calcular multa simbólica por atraso (ex: R$0,50 por dia)
        return daysLate(currentDate) * 0.50;
    }

    private LocalDate getReferenceDate(LocalDate currentDate) {
        return (this.returnDate != null) ? this.returnDate : currentDate;
    }
}