package com.example.bookshop.services;

import com.example.bookshop.entities.Author;
import com.example.bookshop.entities.AuthorNamesWithTotalCopies;
import com.example.bookshop.entities.Book;
import com.example.bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {
private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();
        int authorId = new Random().nextInt((int) size) + 1;
        return this.authorRepository.findById(authorId).get();
    }

    @Override
    public List<Author> findAllByFirstNameEndingWith(String ending) {
        return this.authorRepository.findAllByFirstNameEndingWith(ending);
    }

    @Override
    public List<AuthorNamesWithTotalCopies> getWithTotalCopies() {
        return this.authorRepository.getWithTotalCopies();
    }
}
