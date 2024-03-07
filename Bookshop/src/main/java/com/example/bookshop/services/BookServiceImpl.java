package com.example.bookshop.services;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import com.example.bookshop.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<String> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return bookRepository.findAllByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllTitlesByEditionTypeAndCopiesLessThan(EditionType editionType, int i) {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, i)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal bigDecimal, BigDecimal bigDecimal1) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(bigDecimal, bigDecimal1);
    }

    @Override
    public List<Book> findTitlesForBooksNotReleasedAtYear(int year) {
        return bookRepository.findAllByReleaseDateLessThanOrReleaseDateGreaterThan(
                LocalDate.of(year,1,1),
                LocalDate.of(year, 12, 31)
                );

    }

    @Override
    public List<Book> findAllByReleaseDateLessThan(LocalDate localDate) {
        return bookRepository.findAllByReleaseDateLessThan(localDate);
    }

    @Override
    public List<Book> findAllByTitleContaining(String input) {
        return bookRepository.findAllByTitleContaining(input);
    }
}
