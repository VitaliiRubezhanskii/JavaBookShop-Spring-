package ua.rubezhanskii.javabookshop.reports;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;


public class ExcelFileBean {


    public ExcelFileBean() {
      //  this.file = new File("");//"C:\\Users\\Vitalii\\Desktop\\Books.xls"
    }

    private CommonsMultipartFile multipartFile;

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public CommonsMultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(CommonsMultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}