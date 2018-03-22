package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.datamanagement.repository.AuthorService;
import ua.rubezhanskii.javabookshop.herokuspecific.HerokuHelper;
import ua.rubezhanskii.javabookshop.model.Author;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorJdbcTemplate implements AuthorService {

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public AuthorJdbcTemplate(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void postConstruct() {

        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("author")
                .usingGeneratedKeyColumns("authorId");
    }

    @Override
    public void update(Author author) {
        final String UPDATE_AUTHOR="UPDATE author set author1=?, author2=?,author3=?,author4=? WHERE authorId=?";
        jdbcTemplate.update(UPDATE_AUTHOR,author.getAuthor1(),author.getAuthor2(),author.getAuthor3(),author.getAuthor4(),author.getAuthorId());
    }

    @Override
    public Integer save(Author author) {
        try{
    if (author.getAuthorId().equals(getAuthorOfBook(author.getAuthorId()).getAuthorId())){
    update(author);

    }else {

    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(author);
    Number number = jdbcInsert.executeAndReturnKey(parameterSource);
    if (number != null) {
        return number.intValue();
         }
    throw new RuntimeException("Cannot retrieve primary key");
     }

        /*
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO author(author1, author2, author3, author4) VALUES (?,?,?,?)",
                author.getAuthor1(),author.getAuthor2(),author.getAuthor3(),author.getAuthor4(),keyHolder);
        Number number=keyHolder.getKey();
        return  number.intValue();*/
    }catch (Exception ex){
            new HerokuHelper().save(author);
        }
        return 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Author getAuthorOfBook(Integer authorId){
        final String AUTHOR_OF_BOOK="Select * from author WHERE  authorId=?";
        return (Author)jdbcTemplate.queryForObject(AUTHOR_OF_BOOK, new Object[]{authorId}, new AuthorRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Author getAuthorByName(String name){
        final String AUTHOR_OF_BOOK="Select * from author WHERE  author1=?";
        return (Author)jdbcTemplate.queryForObject(AUTHOR_OF_BOOK, new Object[]{name}, new AuthorRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean exists(String author) {
        List<Author> authors=(List<Author>)jdbcTemplate.query("SELECT * FROM author WHERE author1=?",new Object[]{author},
                new AuthorRowMapper());
        return authors.size()>=1;
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
