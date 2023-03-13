package com.example.pidev1.Controller;

import com.example.pidev1.Entity.Student;
import com.example.pidev1.Service.StudentPDFExporter;
import com.example.pidev1.Service.StudentServiceImp;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Etudiant")
public class StudentController {
    @Autowired
    StudentServiceImp iStudentServices;
    @GetMapping("/getStudent")
    List<Student> getStudent() {
        return iStudentServices.retrieveAllStudents();
    }

    @PostMapping ("/addStudent")
    Student addStudent(@RequestBody Student s) {
        return iStudentServices.addStudent(s);
    }

    @PutMapping("/update/{id}")
    Student update(@RequestBody Student s , @PathVariable("id") Long idStudent) {
        return iStudentServices.update(s, idStudent);
    }

    @DeleteMapping("/delete/{id}")
    void removeStudent (@PathVariable("id") Long idStudent) {
        iStudentServices.removeStudent(idStudent);
        
    }

    @GetMapping("/sugg/{id}")
    public String Suggestion(@PathVariable("id") Long idStudent) {
        return iStudentServices.Suggestion(idStudent);
    }
    /*

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file to disk
            file.transferTo(new File("path/to/save/file/" + file.getOriginalFilename()));
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
*/
    @Autowired
    private StudentServiceImp service;

    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Student> listStudent = service.listAll();

        StudentPDFExporter exporter = new StudentPDFExporter(listStudent);
        exporter.export(response);

    }


}

