package com.example.bookshop.services;

import com.example.bookshop.entities.AgeRestriction;
import com.example.bookshop.entities.Book;
import com.example.bookshop.entities.EditionType;

import java.util.List;

public interface BookService {
    List<String> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int i);

}
