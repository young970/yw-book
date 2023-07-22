package com.prgms.ywbook.rental.domain;

import com.prgms.ywbook.book.domain.Author;
import com.prgms.ywbook.book.domain.Book;
import com.prgms.ywbook.book.domain.Title;
import com.prgms.ywbook.global.exception.NotUpdateException;
import com.prgms.ywbook.member.domain.Member;
import com.prgms.ywbook.member.domain.PhoneNumber;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcRentalRepository implements RentalRepository {
    private static final RowMapper<JoinedRental> joinedRentalRowMapper = (resultSet, i) -> {
        UUID rentalId = UUID.fromString(resultSet.getString("R.id"));
        LocalDateTime rentedAt = resultSet.getTimestamp("R.rented_at").toLocalDateTime();

        UUID memberId = UUID.fromString(resultSet.getString("R.member_id"));
        PhoneNumber phoneNumber = new PhoneNumber(resultSet.getString("M.phone_number"));

        UUID bookId = UUID.fromString(resultSet.getString("R.book_id"));
        Title title = new Title(resultSet.getString("B.title"));
        Author author = new Author(resultSet.getString("B.author"));
        boolean available = resultSet.getBoolean("B.available");

        Member member = new Member(memberId, phoneNumber);
        Book book = new Book(bookId, title, author, available);
        return new JoinedRental(rentalId, member, book, rentedAt);
    };

    private static final RowMapper<Rental> rentalRowMapper = (resultSet, i) -> {
        UUID rentalId = UUID.fromString(resultSet.getString("id"));
        UUID memberId = UUID.fromString(resultSet.getString("member_id"));
        UUID bookId = UUID.fromString(resultSet.getString("book_id"));
        LocalDateTime rentedAt = resultSet.getTimestamp("rented_at").toLocalDateTime();
        return new Rental(rentalId, memberId, bookId, rentedAt);
    };

    private final JdbcTemplate jdbcTemplate;

    public JdbcRentalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rental insert(Rental rental) {
        String sql = "INSERT INTO book(id, member_id, book_id, rented_at) VALUES (?, ?, ?, ?)";
        int update = jdbcTemplate.update(sql,
                rental.getId().toString(),
                rental.getMemberId().toString(),
                rental.getBookId().toString(),
                Timestamp.valueOf(rental.getRentedAt()));
        if (update != 1) {
            throw new NotUpdateException("insert가 제대로 이루어지지 않았습니다.");
        }
        return rental;
    }

    @Override
    public Optional<Rental> findById(UUID rentalId) {
        String sql = "SELECT id, member_id, book_id, rented_at FROM rental WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    rentalRowMapper,
                    rentalId.toString()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<JoinedRental> findJoinedRentalById(UUID joinedRentalId) {
        String sql = "SELECT R.id, R.member_id, R.member_id, R.rented_at, M.phone_number, B.phone_number, B.author, B.available " +
                "FROM rental R " +
                "INNER JOIN member M ON R.member_id = M.id " +
                "INNER JOIN book B ON R.book_id = B.id " +
                "WHERE R.id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    joinedRentalRowMapper,
                    joinedRentalId.toString()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<JoinedRental> findJoinedRentalByPhoneNumber(PhoneNumber number) {
        String sql = "SELECT R.id, R.member_id, R.member_id, R.rented_at, M.phone_number, B.phone_number, B.author, B.available " +
                "FROM rental R " +
                "INNER JOIN member M ON R.member_id = M.id " +
                "INNER JOIN book B ON R.book_id = B.id " +
                "WHERE M.phone_number = ?";
        return jdbcTemplate.query(sql, joinedRentalRowMapper, number.getNumber());
    }

    @Override
    public List<JoinedRental> findJoinedRentalByRentalAt(LocalDateTime time) {
        String sql = "SELECT R.id, R.member_id, R.member_id, R.rented_at, M.phone_number, B.phone_number, B.author, B.available " +
                "FROM rental R " +
                "INNER JOIN member M ON R.member_id = M.id " +
                "INNER JOIN book B ON R.book_id = B.id " +
                "WHERE DATEDIFF(?, R.rented_at) > 14";
        return jdbcTemplate.query(sql, joinedRentalRowMapper, Timestamp.valueOf(time));
    }

    @Override
    public void deleteById(UUID rentalId) {
        String sql = "DELETE FROM rental WHERE id = ?";
        int update = jdbcTemplate.update(sql, rentalId.toString());
        if (update != 1) throw new NotUpdateException("db에 delete가 수행되지 못했습니다.");
    }
}
