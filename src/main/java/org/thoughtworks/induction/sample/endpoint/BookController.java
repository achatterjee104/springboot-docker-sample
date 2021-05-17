package org.thoughtworks.induction.sample.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thoughtworks.induction.sample.bean.Book;
import org.thoughtworks.induction.sample.service.BookService;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    List<Book> list() {
        return bookService.fetchAll();
    }
}
