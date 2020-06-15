package com.example.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    public List<Book> findAllByAuthorContainingOrDescriptionContaining(String text, String textAgain);
}
