package com.example.bookshop;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        //seedService.seedAll();
        //this.booksAfter2000();
        //this.allAuthorsWithBooksBefore1990();
        //this.allAuthorsOrderByBooksCount();
        //this.allBooksByGeorgePowell();
        //printBooksByAgeRestriction();
        //printGoldenBooksWithLessThan5000Copies();
        //printBooksWithPriceOutOfRange();
        //printBooksNotIssuedAt();
        //printBookInfoForBooksReleasedBefore();
        //printNamesOfAuthorsWithEndingWith();
        //printBookTitlesContaining();
        //printBooksByAuthorLastNameStartsWith();
        //printCountOfBooksWithTitleLongerThan();
        //printTotalCopiesByAuthor();
        //printInformationForTitle();
        //increaseBookCopies();
        removeBooks();

    }

    private void removeBooks() {
        Scanner scanner = new Scanner(System.in);
        int amount = Integer.parseInt(scanner.nextLine());
        int removedBooks = bookService.removeBooksWithCopiesLessThan(amount);
        System.out.printf("%d books were deleted", removedBooks);
    }

    private void increaseBookCopies() {
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        int copies = Integer.parseInt(scanner.nextLine());
        int totalCopies = bookService.increaseCopies(date, copies);
        System.out.printf("%d books are released after %s, so total of %d book copies were added", totalCopies, date, copies * totalCopies);
    }

    private void printInformationForTitle() {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        Book book = bookService.findByTitle(title);
        System.out.println(book.getTitle() + " " + book.getEditionType() + " " + book.getAgeRestriction() + " " + book.getPrice());
    }

    private void printTotalCopiesByAuthor() {
       this.authorService.getWithTotalCopies()
               .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " - " + a.getTotalCopies()));
    }

    private void printCountOfBooksWithTitleLongerThan() {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        int count = bookService.countOfBooksWithTitleLongerThan(length);
        System.out.printf("There are %d books with longer title than %d symbols%n",count, length);
    }

    private void printBooksByAuthorLastNameStartsWith() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Book> books = bookService.findAllByAuthorLastNameStartsWith(input);
        books.forEach(b -> System.out.println(b.getTitle() + " (" + b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName() + ")"));
    }

    private void printBookTitlesContaining() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Book> books = bookService.findAllByTitleContaining(input);
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void printNamesOfAuthorsWithEndingWith() {
        //find all authors with names ending with
        Scanner scanner = new Scanner(System.in);
        String ending = scanner.nextLine();
        List<Author> authors = authorService.findAllByFirstNameEndingWith(ending);
        authors.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void printBookInfoForBooksReleasedBefore() {
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Book> books = bookService.findAllByReleaseDateLessThan(localDate);
        books.forEach(b -> System.out.println(b.getTitle() + " " + b.getEditionType() + " " + b.getPrice()));
    }

    private void printBooksNotIssuedAt() {
        List<Book> titlesForBooksNotPublishedIn = bookService.findTitlesForBooksNotReleasedAtYear(2000);
        titlesForBooksNotPublishedIn.forEach(b -> System.out.println(b.getTitle()));
    }

    private void printBooksWithPriceOutOfRange() {
        List<Book> books = bookService.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
        books.forEach(b -> System.out.println(b.getTitle() + " - $" + b.getPrice()));
    }

    private void printGoldenBooksWithLessThan5000Copies() {
        bookService.findAllTitlesByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
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
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName() + " " + author.getBooks().size()));

    }

    private void allAuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);
        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);
        authors.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 12, 31);
        List<Book> books = this.bookRepository.findByReleaseDateNot(year2000);

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
