package com.ra.service;

import com.ra.model.entity.Author;
import com.ra.model.entity.StatusDeleteAuthor;
import com.ra.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Author creatAuthor(Author author) {
        return authorRepository.create(author);
    }
    public Author getAuthorById(int id) {
        return authorRepository.findById(id);
    }
    public Author updateAuthor(Author author, int id) {
        return authorRepository.update(author, id);
    }
    public StatusDeleteAuthor deleteAuthor(int id) {
        Author author = authorRepository.findById(id);
        if (author != null) {
            if(author.getName().equalsIgnoreCase("admin")) {
                return StatusDeleteAuthor.ERR_BAD_REQUEST;
            }
            authorRepository.delete(id);
            return StatusDeleteAuthor.DELETED;
        }
        return StatusDeleteAuthor.ERR_NOT_FOUND;
    }
}
