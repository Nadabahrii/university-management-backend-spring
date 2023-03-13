package com.example.pidev1.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
/*
@Controller
public class PDFToTextController {
    @Autowired
    private PDFToTextConverter pdfToTextConverter;

    @GetMapping("/convert")
    public String convertPDFToText(@RequestParam("file") String filePath, Model model) {
        try {
            String text = pdfToTextConverter.convertPDFToText(filePath);
            model.addAttribute("text", text);
            return "text";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

}
*/