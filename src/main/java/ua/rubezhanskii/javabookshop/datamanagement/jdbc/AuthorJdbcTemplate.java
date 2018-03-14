package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.datamanagement.repository.AuthorService;
import ua.rubezhanskii.javabookshop.model.Author;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthorJdbcTemplate implements AuthorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    private SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    private void postConstruct() {

        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("author")
                .usingGeneratedKeyColumns("authorId");

    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update("UPDATE author set author1=?, author2=?,author3=?,author4=? WHERE authorId=?",
                author.getAuthor1(),author.getAuthor2(),author.getAuthor3(),author.getAuthor4(),author.getAuthorId());
    }

    @Override
    public Integer save(Author author) {

       SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(author);
        Number number=jdbcInsert.executeAndReturnKey(parameterSource);
        if (number!=null){
            return number.intValue();
        }
        throw new RuntimeException("Cannot retrieve primary key");
/*
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO author(author1, author2, author3, author4) VALUES (?,?,?,?)",
                author.getAuthor1(),author.getAuthor2(),author.getAuthor3(),author.getAuthor4(),keyHolder);
        Number number=keyHolder.getKey();
        return  number.intValue();*/
    }



    @Override
    public Author getAuthorOfBook(Integer authorId){
        return (Author)jdbcTemplate.queryForObject("Select * from author WHERE  authorId=?", new Object[]{authorId},  new AuthorRowMapper());
    }


    private class AuthorRowMapper implements RowMapper {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author=new Author();
            author.setAuthorId(rs.getInt("authorId"));
            author.setAuthor1(rs.getString("author1"));
            author.setAuthor2(rs.getString("author2"));
            author.setAuthor3(rs.getString("author3"));
            author.setAuthor4(rs.getString("author4"));
            return author;
        }
    }
}
