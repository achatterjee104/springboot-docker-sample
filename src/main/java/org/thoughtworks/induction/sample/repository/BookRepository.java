package org.thoughtworks.induction.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thoughtworks.induction.sample.bean.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {
    List<Book> findAllByOrderByPriceDesc();
}
