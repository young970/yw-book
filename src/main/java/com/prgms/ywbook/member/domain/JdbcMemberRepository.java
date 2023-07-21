package com.prgms.ywbook.member.domain;

import com.prgms.ywbook.global.exception.NotUpdateException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;
import java.util.UUID;

public class JdbcMemberRepository implements MemberRepository {
    private final RowMapper<Member> memberRowMapper = (resultSet, i) -> {
        UUID memberId = UUID.fromString(resultSet.getString("id"));
        PhoneNumber number = new PhoneNumber(resultSet.getString("phone_number"));
        return new Member(memberId, number);
    };

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Member> findById(UUID memberId) {
        String sql = "SELECT id, phone_number FROM member WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    memberRowMapper,
                    memberId.toString()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByNumber(String phoneNumber) {
        String sql = "SELECT id, phone_number FROM member WHERE phone_number = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    memberRowMapper,
                    phoneNumber));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Member insert(Member member) {
        String sql = "INSERT INTO member(id, phone_number) VALUES (?, ?)";
        int update = jdbcTemplate.update(sql,
                member.getMemberId().toString(),
                member.getPhoneNumber());
        if (update != 1) {
            throw new NotUpdateException("insert가 제대로 이루어지지 않았습니다.");
        }
        return member;
    }
}
