
package com.example.kadaiapi.repository;

import com.example.kadaiapi.dto.Book;
import com.example.kadaiapi.mapper.BookMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final BookMapper bookMapper;

    public BookRepository(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    public Book insert(Book book) {
        int result = bookMapper.insert(book);

        if (result != 1) {
            throw new IllegalStateException("本を登録できませんでした");
        }

        return bookMapper.findById(book.getId());
    }

    public Book findById(long id) {
        return bookMapper.findById(id);
    }

    public Book update(Book book) {
        int result = bookMapper.update(book);

        if (result != 1) {
            return null;
        }

        return bookMapper.findById(book.getId());
    }

    public boolean delete(long id) {
        return bookMapper.delete(id) == 1;
    }
}
