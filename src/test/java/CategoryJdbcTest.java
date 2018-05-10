
import helpers.CategoryRowHandler;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.rubezhanskii.javabookshop.model.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;



public class CategoryJdbcTest {

    private Connection connection;
    private Statement statement;
   @Before
    public void setUpDataBase() throws Exception{
       Class.forName("org.h2.Driver");
     connection= DriverManager.getConnection("jdbc:h2:~/testDb","sa","");
     statement=connection.createStatement();
     statement.execute("CREATE TABLE category\n" +
             "(\n" +
             "  categoryId  SERIAL PRIMARY KEY,\n" +
             "\n" +
             "  category   VARCHAR(30) NULL\n" +
             ");");
     statement.execute("INSERT INTO category (categoryId, category) VALUES\n" +
             "  (1, 'Spring'),\n" +
             "  (2, 'OOP and Patterns'),\n" +
             "  (3, 'Java SE'),\n" +
             "  (4, 'Multithreading'),\n" +
             "  (5, 'Hibernate'),\n" +
             "  (6, 'Java EE'),\n" +
             "  (7, 'Testing'),\n" +
             "  (8, 'Scala'),\n" +
             "  (9, 'REST'),\n" +
             "  (10, 'test'),\n" +
             "  (11, 'test');");
   }

    @Test
    public void save_Category_Should_save_Only_One_Row() throws Exception{

        QueryRunner runner=new QueryRunner();
             String testQuery="insert into category(category) values(?)";
               int rowsInserted=runner.update(connection,testQuery,"TestCategory");
                     assertEquals(rowsInserted,1);
    }
    @Test
    public void save_Category_Should_save_Expected_ID() throws Exception{

        QueryRunner runner=new QueryRunner();
        String testQuery="insert into category(category) values(?)";
        int returnedId=runner.insert(connection,testQuery,new ScalarHandler<Integer>(),
                "TestCategory");
        assertEquals(returnedId,12);
    }
    @Test
    public void getAllCategories_With_correct_Fields() throws Exception{

        QueryRunner runner=new QueryRunner();
            String query="select * from category";
                 List<Category> categories= (List<Category>)runner.query(connection,query,new CategoryRowHandler(connection));
                    assertEquals((int)categories.get(7).getCategoryId(),8);
                    assertEquals(categories.get(7).getCategory(),"Scala");
                    assertEquals(categories.size(),11);
    }

    @Test
    public void delete_Category_Should_delete_Only_One_Row() throws Exception{

        QueryRunner runner=new QueryRunner();
            String testQuery="delete from category where categoryId=?";
                int deletedRows=runner.update(connection,testQuery,"3");
                     assertEquals(deletedRows,1);
    }

    @Test
    public void update_Category_Should_update_4_Rows_When_ID_Less_Than_5() throws Exception{

        QueryRunner runner=new QueryRunner();
            String testQuery="update category set category=? where categoryId<5";
                 int updatedRows=runner.update(connection,testQuery,"testCategory");
                     assertEquals(updatedRows,4);
    }


    @Test
    public void get_Category_By_Name_Return_Category_With_correct_Fields() throws Exception{

        QueryRunner runner=new QueryRunner();
            String query="select * from category where category=?";
                 List<Category> category= (List<Category>)runner.query(connection,query,new CategoryRowHandler(connection),new Object[]{"Scala"});
                        assertEquals((int)category.get(0).getCategoryId(),8);
                        assertEquals(category.get(0).getCategory(),"Scala");
                        assertEquals(category.size(),1);
    }

    @Test
    public  void test_If_Category_with_given_CategoryName_exists() throws Exception{

        QueryRunner runner=new QueryRunner();
             String query="select * from category where category=?";
                    List<Category> category= (List<Category>)runner.query(connection,query,new CategoryRowHandler(connection),new Object[]{"Scala"});
                         assertNotNull(category.get(0));
    }

    @Test
    public  void test_If_Category_with_given_categoryName_NOT_exists() throws Exception{

        QueryRunner runner=new QueryRunner();
             String query="select * from category where category=?";
                 List<Category> category= (List<Category>)runner.query(connection,query,new CategoryRowHandler(connection),new Object[]{"Concurrency"});
                     assertEquals(category.size(),0);
    }

    @After
    public void tearDown() throws Exception{
        statement.execute("DROP TABLE category");
        DbUtils.closeQuietly(statement);
        DbUtils.closeQuietly(connection);

    }




    }



