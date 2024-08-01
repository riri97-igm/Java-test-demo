package team6.java.ca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team6.java.ca.services.CSVGenerationService;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class CSVController {

    @Autowired
    private CSVGenerationService csvGenerationService;

    @GetMapping("/generateCurrentLeaveCSV")
    public ResponseEntity<InputStreamResource> generateCurrentLeaveCSV(@RequestParam String filePath) {
        try {
            csvGenerationService.generateCSVForCurrentLeaveEmployees(filePath);

            FileInputStream fileInputStream = new FileInputStream(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData("attachment", "CurrentLeaveEmployees.csv");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(fileInputStream));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/generateManagerLeaveCSV")
    public ResponseEntity<InputStreamResource> generateManagerLeaveCSV(@RequestParam Long managerId, @RequestParam String filePath) {
        try {
            csvGenerationService.generateCSVForLeaveRecordsUnderManager(managerId, filePath);

            FileInputStream fileInputStream = new FileInputStream(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData("attachment", "ManagerLeaveEmployees.csv");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(fileInputStream));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

