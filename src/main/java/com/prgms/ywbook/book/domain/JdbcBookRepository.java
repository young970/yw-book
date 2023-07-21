package com.prgms.ywbook.book.domain;

import com.prgms.ywbook.global.exception.NotUpdateException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcBookRepository implements BookRepository{
    private final RowMapper<Book> bookRowMapper = (resultSet, i) -> {
        UUID bookId = UUID.fromString(resultSet.getString("id"));
        Title title = new Title(resultSet.getString("title"));
        Author author = new Author(resultSet.getString("author"));
        boolean available = resultSet.getBoolean("available");
        return new Book(bookId, title, author, available);
    };

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Book> findById(UUID bookId) {
        String sql = "SELECT id, author, available FROM book WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    bookRowMapper,
                    bookId.toString()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Book insert(Book book) {
        String sql = "INSERT INTO book(id, title, author, available) VALUES (?, ?, ?, ?)";
        int update = jdbcTemplate.update(sql,
                book.getId().toString(),
                book.getTitle(),
                book.getAuthor(),
                book.isAvailable());
        if (update != 1) {
            throw new NotUpdateException("insert가 제대로 이루어지지 않았습니다.");
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT id, title, author, available FROM book", bookRowMapper);
    }

    @Override
    public List<Book> findAvailable(boolean available) {
        String sql = "SELECT id, title, author, available FROM book WHERE available = ?";
        return jdbcTemplate.query(sql, bookRowMapper, available);
    }
}
