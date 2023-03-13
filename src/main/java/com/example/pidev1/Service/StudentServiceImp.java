package com.example.pidev1.Service;

import com.example.pidev1.Entity.Student;
import com.example.pidev1.Repository.IStudentRepository;
import com.example.pidev1.filter.Myfilter;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.io.IOException;
import java.io.File;

/*import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;*/

@Service
@Component
public class StudentServiceImp implements IStudentServices {

    @Autowired
    IStudentRepository iStudentRepository;

    @Override
    public void retreiveAllStudents() {}
    @Override
    public List<Student> retrieveAllStudents() {
        return iStudentRepository.findAll();
    }
    @Override
    public Student addStudent(Student s) {

        String output = Myfilter.getCensoredText(s.getDescription_and_motivation()) ;
        System.out.println(output);
        s.setDescription_and_motivation(output);
        return iStudentRepository.save(s);
    }

    //sms
    public static final String ACCOUNT_SID = "AC2420083fb29a62edd5f592cfa12ac98f";
    public static final String AUTH_TOKEN = "c2daf4ab58d08620521e2471488640f5";

    /*public void sendSmsvalide() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message msg = Message.creator(new PhoneNumber()("+21650700441"),new PhoneNumber("+12284600693"),(" msg mta3k ")).create();

    }*/
    @Override
    public Student update(Student s, Long idStudent) {
        return iStudentRepository.save(s);
    }
    @Override
    public void removeStudent(Long idStudent) {iStudentRepository.deleteById(idStudent);}

    @Override
    public Student findStudentById(long id) {
        return iStudentRepository.findById(id).get();
    }

    @Override
    public String Suggestion(Long idStudent) {
        List<Student> s = iStudentRepository.findAll();
        Student student = iStudentRepository.findById(idStudent).orElse(null);
        if (student.getLevel().equals("BAC"))
            return (" Elec OR CIVIL ");

        else if (student.getLevel().equals("Prepa"))
            return ("3B");

        else if (student.getLevel().equals("BAC SIENCE "))
            return (" IT ");
        else if (student.getLevel().equals("BAC INFORMATIQUE "))
            return (" IT ");
        else if (student.getLevel().equals("LICENCE"))
            return ("3er YEAR CIVIL ENGINNERING OR 3B ") ;
        else if (student.getLevel().equals("LICENCE INOFRMATIQUE "))
            return ("3A") ;

        else return ("verify your data ");
    }





    /* @Override
     public String extractTextFromPdf(File file) throws IOException {
         PDDocument document = PDDocument.load(file);
         PDFTextStripper stripper = new PDFTextStripper();
         String text = stripper.getText(document);
         document.close();
         return text;
     }*/
   @Autowired
   private IStudentRepository repo;

    public List<Student> listAll() {
        return repo.findAll(Sort.by("idStudent").ascending());
    }


}
