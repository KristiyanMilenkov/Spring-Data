package com.example.bookshop.services;

import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.AuthorNamesWithTotalCopies;
import com.example.bookshop.entities.Book;

import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();

    List<Author> findAllByFirstNameEndingWith(String ending);

    List<AuthorNamesWithTotalCopies> getWithTotalCopies();
}
