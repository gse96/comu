package Project.comu.Member.Dao;


import Project.comu.Member.Dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int join(MemberDto member) {
        String sql = "INSERT INTO MEMBER VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
            int result = 0;
        try {
            result = jdbcTemplate.update(sql,
                    member.getEmail(),
                    member.getPassword(),
                    member.getPhone(),
                    member.getName(),
                    member.getBirth(),
                    member.getAddress(),
                    member.getGender(),
                    member.getForeigner());

            return result;
        }
        catch (DuplicateKeyException e) {
            result = 0;
        }
        return result;
    }

// 이메일 찾기
    public Optional<MemberDto> findByEmail(String email) {
        String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
        try {
            MemberDto member = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), email);
            return Optional.ofNullable(member);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
// 이메일 찾기 boolean
    public boolean Boolean_findByEmail(String email) {
        String sql = "SELECT * FROM MEMBER WHERE EMAIL = ?";
        boolean result = false;
        try {
            MemberDto answer = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), email);
            if(answer != null) {
                result = false;
            }
        }
        catch (EmptyResultDataAccessException e) {
            result = true;
        }
        return result;
    }

    public boolean Boolean_findByPhone(String phone) {
        String sql = "SELECT * FROM MEMBER WHERE PHONE = ?";
        boolean result = false;
        try {
            MemberDto answer = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), phone);
            if(answer != null) {
                result = false;
            }
        }
        catch (EmptyResultDataAccessException e) {
            result = true;
        }
        return result;
    }
}
