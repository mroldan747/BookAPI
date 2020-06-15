package com.example.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findAllByAuthorContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting book
        Book blogToUpdate = bookRepository.findById(id).get();
        blogToUpdate.setTitle(book.getTitle());
        blogToUpdate.setDescription(book.getDescription());
        return bookRepository.save(blogToUpdate);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}
