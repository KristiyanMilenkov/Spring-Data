package com.example.bookshop.repositories;

import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.AuthorNamesWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);


    List<Author> findAllByFirstNameEndingWith(String ending);

    @Query("SELECT a.firstName as firstName, a.lastName as lastName, SUM(b.copies) as totalCopies" +
            " FROM Authors a" +
            " JOIN a.books as b" +
            " GROUP BY b.author" +
            " ORDER BY totalCopies DESC")
    List<AuthorNamesWithTotalCopies> getWithTotalCopies();
}
