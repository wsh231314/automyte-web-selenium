package com.auto.myte.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils  {

    public static Workbook buildExcelDocument() throws Exception {

    	Workbook workbook = new HSSFWorkbook();
    	
        // create excel xls sheet
        Sheet sheet = workbook.createSheet("User Detail");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);


        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Firstname");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("LastName");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Age");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Job Title");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Company");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Address");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("City");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Country");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("Phone Number");
        header.getCell(8).setCellStyle(style);



        int rowCount = 1;

//        for(User user : users){
//            Row userRow =  sheet.createRow(rowCount++);
//            userRow.createCell(0).setCellValue(user.getFirstName());
//            userRow.createCell(1).setCellValue(user.getLastName());
//            userRow.createCell(2).setCellValue(user.getAge());
//            userRow.createCell(3).setCellValue(user.getJobTitle());
//            userRow.createCell(4).setCellValue(user.getCompany());
//            userRow.createCell(5).setCellValue(user.getAddress());
//            userRow.createCell(6).setCellValue(user.getCity());
//            userRow.createCell(7).setCellValue(user.getCountry());
//            userRow.createCell(8).setCellValue(user.getPhoneNumber());
//
//            }
        return workbook;
        
    }
    
    public File createNewFile() {  
        String path = "";
        File file = new File("template.xlsx");  
        String newFileName = "new_test" + ".xlsx";  
        // write new excel file
        File newFile = new File(newFileName);  
        try {  
            newFile.createNewFile();  
            // copy template to new file
            fileChannelCopy(file, newFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return newFile;  
    }  
    
    public void fileChannelCopy(File s, File t) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(s), 1024);  
                out = new BufferedOutputStream(new FileOutputStream(t), 1024);  
                byte[] buffer = new byte[1024];  
                int len;  
                while ((len = in.read(buffer)) != -1) {  
                    out.write(buffer, 0, len);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
