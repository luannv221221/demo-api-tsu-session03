package com.ra.controller;

import com.ra.model.entity.Author;
import com.ra.model.entity.StatusDeleteAuthor;
import com.ra.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping("/api/v1/authors")
    public ResponseEntity<?> findAll() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
    @PostMapping("/api/v1/authors")
    public ResponseEntity<?> create(@RequestBody Author author) {
        Author authorCreated = authorService.creatAuthor(author);
        return new ResponseEntity<>(authorCreated, HttpStatus.CREATED);
    }
    @GetMapping("/api/v1/authors/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/api/v1/authors/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        Author authorUpdated = authorService.updateAuthor(author, id);
        if (authorUpdated != null) {
            return new ResponseEntity<>(authorUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/v1/authors/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable int id) {
        StatusDeleteAuthor statusDeleteAuthor  = authorService.deleteAuthor(id);
        return switch (statusDeleteAuthor) {
            case DELETED -> new ResponseEntity<>(HttpStatus.NO_CONTENT);
            case ERR_BAD_REQUEST -> new ResponseEntity<>("Không được phép xóa tài khoản quản trị (Admin)",HttpStatus.BAD_REQUEST);
            default -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
        };
    }
}
