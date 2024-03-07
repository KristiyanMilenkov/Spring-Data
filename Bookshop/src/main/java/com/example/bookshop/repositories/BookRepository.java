package com.example.bookshop.repositories;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByReleaseDateNot(LocalDate releaseDate);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int i);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal bigDecimal, BigDecimal bigDecimal1);

    List<Book> findAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate releaseDate, LocalDate of);

    List<Book> findAllByReleaseDateLessThan(LocalDate releaseDate);  //before works too!

    List<Book> findAllByTitleContaining(String input);

    List<Book> findAllByAuthorLastNameStartsWith(String input);

    Book findByTitle(String title);

    @Modifying
    @Transactional
    @Query("update Books b set b.copies = b.copies + :copies where b.releaseDate > :localDate")
    int addCopiesToBooksAfter(LocalDate localDate, int copies);

    @Transactional
    int deleteByCopiesLessThan(int amount);
}
