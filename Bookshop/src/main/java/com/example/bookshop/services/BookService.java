package com.example.bookshop.services;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    List<String> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllTitlesByEditionTypeAndCopiesLessThan(EditionType editionType, int i);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal bigDecimal, BigDecimal bigDecimal1);

    public List<Book> findTitlesForBooksNotReleasedAtYear(int i);

    List<Book> findAllByReleaseDateLessThan(LocalDate localDate);

    List<Book> findAllByTitleContaining(String input);

    List<Book> findAllByAuthorLastNameStartsWith(String input);

    int countOfBooksWithTitleLongerThan(int length);


    Book findByTitle(String title);

    int increaseCopies(String localDate, int copies);

    int removeBooksWithCopiesLessThan(int amount);
}
