package com.example.kadaiapi.mapper;

import com.example.kadaiapi.dto.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("""
        SELECT id, title, author, genre,
               created_at AS "createdAt"
        FROM books
        ORDER BY id ASC
        """)
    List<Book> findAll();

    @Insert("""
        INSERT INTO books (title, author, genre)
        VALUES (#{title}, #{author}, #{genre})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Book book);

    @Select("""
        SELECT id, title, author, genre,
               created_at AS "createdAt"
        FROM books
        WHERE id = #{id}
        """)
    Book findById(long id);

    @Update("""
        UPDATE books
        SET title = #{title},
            author = #{author},
            genre = #{genre}
        WHERE id = #{id}
        """)
    int update(Book book);

    @Delete("DELETE FROM books WHERE id = #{id}")
    int delete(long id);
}
