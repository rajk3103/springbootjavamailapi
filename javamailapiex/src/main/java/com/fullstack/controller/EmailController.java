package com.fullstack.controller;

import com.fullstack.model.Emailmodel;
import com.fullstack.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody Emailmodel emailmodel){

        log.info("Sending Email to"+emailmodel.getToEmail());
        emailService.sendEmail(emailmodel);

        return ResponseEntity.ok("Email sent Successfully");
    }
}
