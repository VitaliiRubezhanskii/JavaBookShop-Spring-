package ua.rubezhanskii.javabookshop.reports;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.herokuspecific.HerokuHelper;
import ua.rubezhanskii.javabookshop.model.Author;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ExcelImportServiceImpl implements ExcelImportService {

    @Autowired
    private BookService bookService;
    @Autowired
    private HerokuHelper herokuHelper;

    private static final Logger LOGGER = Logger.getLogger(ExcelImportServiceImpl.class.getName() );

    public void importFile(String fileLocation ){//ExcelFileBean fileBean) {
        LOGGER.info("Start importFile-method");
      /*  if (fileBean==null){
            throw new RuntimeException("Bullshit fileBean == null");
        }


*/
//"C:\\Users\\Vitalii\\Desktop\\Books.xls"

        try {
            //  fileBean.getMultipartFile().transferTo(new File("C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.27\\bin\\","Books.xls"));
          //  copyFile(new File(fileBean.getFile().getAbsolutePath()),new File("C:\\Program Files\\Apache Software Foundation\\Apache Tomcat 8.0.27\\bin\\"+fileBean.getFile().getName()));
           // LOGGER.info("create IS: "+fileBean.getFile().getAbsolutePath()+" fileBean.getFile()= "+fileBean.getFile());
            FileInputStream fileInputStream=new FileInputStream(new File(fileLocation));
            // ByteArrayInputStream bis = new ByteArrayInputStream(fileBean.getFile().getBytes());
/*
            if (fileBean.getFileData().getOriginalFilename().endsWith("xls")) {
                workbook = new HSSFWorkbook(bis);

            } else if (fileBean.getFileData().getOriginalFilename().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(bis);
            } else {
                throw new IllegalArgumentException("Received file does not have a standard excel extension.");
            }*/
            LOGGER.info("create sheet object");
            Workbook workbook=new HSSFWorkbook(fileInputStream);
            // List<Book> bookList=new ArrayList<Book>();

            LOGGER.info("get 1 sheet object");
            Sheet sheet=workbook.getSheetAt(0);
            LOGGER.info("Start iterating rows");
            for (int i=1;i<=sheet.getLastRowNum();i++) {
                Row row=sheet.getRow(i);
                int bookId=(int)row.getCell(0).getNumericCellValue();
                String ISBN=row.getCell(1).getStringCellValue();
                String title=row.getCell(2).getStringCellValue();
                String author=row.getCell(3).getStringCellValue();;
                String category=row.getCell(4).getStringCellValue();;
                String publisher=row.getCell(5).getStringCellValue();
                String language=row.getCell(6).getStringCellValue();
                int inventory=(int)row.getCell(7).getNumericCellValue();
                int bookQauntity=(int)row.getCell(8).getNumericCellValue();
                LOGGER.info("Mapping book fields");
                Book book=new Book();
                Author author1=new Author();
                Category category1=new Category();
                book.setISBN(ISBN);
                book.setBookTitle(title);
                author1.setAuthor1(author);
                book.setAuthor(author1);
                category1.setCategory(category);
                book.setCategory(category1);
                book.setPublisher(publisher);
                book.setLanguage(language);
                book.setInventoryStock(inventory);
                book.setBookQuantity(bookQauntity);
                LOGGER.info("Saving Book object");
                herokuHelper.save(book);

           /*     List<String> cellValues=new ArrayList<>();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                         cellValues.add(cell.getStringCellValue());

                        //go from cell to cell and do create sql based on the content



                 bookList.add(map(cellValues));

            }*/
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void copyFile(File source,File dest) throws IOException{

        FileUtils.copyFile(source,dest);


    }
}



       /*     private Book  map(List<String> cellValues) {
                Book book=new Book();
                Author author=new Author();
                Category category=new Category();
                book.setBookId(Integer.valueOf(cellValues.get(0)));
                book.setISBN(cellValues.get(1));
                book.setBookTitle(cellValues.get(2));
                author.setAuthor1(cellValues.get(3));
                book.setAuthor(author);
                category.setCategory(cellValues.get(4));
                book.setCategory(category);
                book.setPublisher(cellValues.get(5));
                book.setLanguage(cellValues.get(6));
                book.setInventoryStock(Integer.valueOf(cellValues.get(7)));
                book.setBookQuantity(Integer.valueOf(cellValues.get(8)));
                return book;
            }


        }*/



