package com.example.pidev1.Controller;

import com.example.pidev1.Service.QRCodeGenerator;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
public class QRCodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @GetMapping(value = "/qr-code/{content}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@PathVariable String content) throws WriterException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BitMatrix bitMatrix = qrCodeGenerator.generateQRCode(content);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        byte[] bytes = outputStream.toByteArray();
        String base64Encoded = Base64.getEncoder().encodeToString(bytes);

        return ResponseEntity.ok().body(bytes);
    }
}
