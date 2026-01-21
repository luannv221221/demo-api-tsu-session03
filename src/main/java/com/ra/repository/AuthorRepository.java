package com.ra.repository;

import com.ra.model.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();
    public AuthorRepository() {
        Author author = new Author(1,"Nguyen Du","nguyen@gmail.com");
        Author author1 = new Author(2,"Nguyen Du1","nguyen1@gmail.com");
        Author author2 = new Author(3,"Nguyen Du2","nguyen2@gmail.com");
        this.authors.add(author);
        this.authors.add(author1);
        this.authors.add(author2);
    }
    public List<Author> findAll() {
        return this.authors;
    }
}
