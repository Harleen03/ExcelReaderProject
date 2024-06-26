package com.example.excel.controller;

import com.example.excel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/print-student-data")
    public void printStudentData() {
        String filePath = "C:\\Users\\dell\\Downloads\\report.xlsx";
        Map<String, Map<String, Integer>> studentData = studentService.readExcelData(filePath);

        for (Map.Entry<String, Map<String, Integer>> entry : studentData.entrySet()) {
            String studentName = entry.getKey();
            Map<String, Integer> marks = entry.getValue();
            System.out.println("Student: " + studentName);

            for (Map.Entry<String, Integer> markEntry : marks.entrySet()) {
                String subject = markEntry.getKey();
                int mark = markEntry.getValue();
                System.out.println("    " + subject + ": " + mark);
            }
        }
    }
}
