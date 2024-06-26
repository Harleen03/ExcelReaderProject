package com.example.excel.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class StudentService {

    public Map<String, Map<String, Integer>> readExcelData(String filePath) {
        Map<String, Map<String, Integer>> studentData = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row headerRow = rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String studentName = row.getCell(0).getStringCellValue();
                Map<String, Integer> marks = new HashMap<>();

                for (int i = 1; i < row.getLastCellNum(); i++) {
                    String subject = headerRow.getCell(i).getStringCellValue();
                    int mark = (int) row.getCell(i).getNumericCellValue();
                    marks.put(subject, mark);
                }
                studentData.put(studentName, marks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentData;
    }
}