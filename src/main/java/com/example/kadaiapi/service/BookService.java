
package com.example.kadaiapi.service;

import com.example.kadaiapi.dto.Book;
import com.example.kadaiapi.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(long id) {
        Book book = bookRepository.findById(id);

        if (book == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "指定された本が見つかりません"
            );
        }

        return book;
    }

    public Book saveBook(Book book) {
        validate(book);
        return bookRepository.insert(book);
    }

    public Book update(Book book, long id) {
        validate(book);

        // 更新対象の本があるか確認する
        findById(id);

        book.setId(id);

        Book updatedBook = bookRepository.update(book);

        if (updatedBook == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "指定された本が見つかりません"
            );
        }

        return updatedBook;
    }

    public void delete(long id) {
        if (!bookRepository.delete(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "指定された本が見つかりません"
            );
        }
    }

    private void validate(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "タイトルを入力してください"
            );
        }

        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "著者を入力してください"
            );
        }

        if (book.getGenre() == null || book.getGenre().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "ジャンルを入力してください"
            );
        }
    }
}
