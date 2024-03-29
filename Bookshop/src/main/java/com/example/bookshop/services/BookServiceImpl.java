package com.example.bookshop.services;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import com.example.bookshop.repositories.BookRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Override
    public List<Book> findAllByAuthorLastNameStartsWith(String input) {
        return bookRepository.findAllByAuthorLastNameStartsWith(input);
    }

    @Override
    public int countOfBooksWithTitleLongerThan(int length) {
        return bookRepository.findAll()
                .stream()
                .filter(b -> b.getTitle().length() > length)
                .toList()
                .size();
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public int increaseCopies(String date, int copies) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate after = LocalDate.parse(date, formatter);
        return bookRepository.addCopiesToBooksAfter(after,copies);
    }

    @Override
    public int removeBooksWithCopiesLessThan(int amount) {
        return this.bookRepository.deleteByCopiesLessThan(amount);
    }
}
