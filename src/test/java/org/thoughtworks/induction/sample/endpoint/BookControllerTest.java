package org.thoughtworks.induction.sample.endpoint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.thoughtworks.induction.sample.bean.Book;
import org.thoughtworks.induction.sample.endpoint.BookController;
import org.thoughtworks.induction.sample.service.BookService;
import org.thoughtworks.induction.sample.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@WithMockUser
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    UserService userService;

    @Test
    void shouldListAllBooksWhenPresent() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book = new Book("title", "author name", 300);
        books.add(book);
        when(bookService.fetchAll()).thenReturn(books);

        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
        verify(bookService, times(1)).fetchAll();
    }

    @Test
    void shouldBeEmptyListWhenNoBooksPresent() throws Exception {
        when(bookService.fetchAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
        verify(bookService, times(1)).fetchAll();
    }
}