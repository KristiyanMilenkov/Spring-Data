package com.example.bookshop;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository, BookService bookService) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //  seedService.seedAll();
        // this.booksAfter2000();
        // this.allAuthorsWithBooksBefore1990();
        //this.allAuthorsOrderByBooksCount();
        //this.allBooksByGeorgePowell();
        //printBooksByAgeRestriction();
        printGoldenBooksWithLessThan5000Copies();
    }

    private void printGoldenBooksWithLessThan5000Copies() {
        bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .forEach(System.out::println);
    }

    private void printBooksByAgeRestriction() {
        Scanner scanner = new Scanner(System.in);
        String restriction = scanner.nextLine();
        try {
            AgeRestriction ageRestriction = AgeRestriction.valueOf(restriction.toUpperCase());
            List<String> titles = this.bookService.findAllByAgeRestriction(ageRestriction);
            titles.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid age restriction");
            return;
        }

    }

    private void allAuthorsOrderByBooksCount() {
        List<Author> authors = this.authorRepository.findAll();
        authors.stream()
                        .sorted((l,r) -> r.getBooks().size() - l.getBooks().size())
                        .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName() + " " + author.getBooks().size()));

    }

    private void allAuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);
        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);
        authors.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 12, 31);
        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(book -> System.out.println(book.getTitle()));
    }
    private void allBooksByGeorgePowell() {
        List<Book> books = this.bookRepository.findAll();
        books.stream()
                .filter(book -> book.getAuthor().getFirstName().equals("George") && book.getAuthor().getLastName().equals("Powell"))
                .sorted(Comparator.comparing(Book::getReleaseDate).reversed().thenComparing(Book::getTitle))
                .forEach(book -> System.out.println(book.getTitle() + " " + book.getReleaseDate() + " " + book.getCopies()));
    }


}
