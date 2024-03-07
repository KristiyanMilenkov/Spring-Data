package com.example.bookshop.services;

import com.example.bookshop.entities.Author;

import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();

    List<Author> findAllByFirstNameEndingWith(String ending);
}
