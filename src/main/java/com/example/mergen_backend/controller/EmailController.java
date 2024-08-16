package com.example.mergen_backend.controller;

import com.example.mergen_backend.entity.Intern;
import com.example.mergen_backend.entity.Email;
import com.example.mergen_backend.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.mergen_backend.service.InternService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    private InternService internService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/inbox")
    public List<Email> getSentEmails() {
        return emailService.getSentEmails();
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailsToInterns(@RequestBody Map<String, String> payload) {
        String status = payload.get("status");
        String name = payload.get("name");
        String department = payload.get("department");
        String date = payload.get("date");
        String subject = "Staj Başvurunuz Hakkında";
        String content;

        switch (status) {
            case "Onaylandı":
                content = "Sayın " + name + ", staj başvurunuz "+ date +" tarihi ve "+ department +" departmanı için kabul edilmiştir. Tebrikler! İlgili departman sizinle en kısa zamanda iletişime geçecektir.\n\nİlginiz için teşekkürler!";
                break;
            case "Beklemede":
                content = "Sayın " + name + ", staj başvurunuz "+ date +" tarihi ve "+ department +" departmanı için yedek listeye alınmıştır. İlgili departmanda pozisyon açıldığında sizinle iletişime geçecektir.\n\nİlginiz için teşekkürler!";
                break;
            case "Reddedildi":
                content = "Sayın " + name + ", staj başvurunuz maalesef reddedilmiştir. İlginiz için teşekkür ederiz ve gelecekteki başvurularınızda başarılar dileriz.\n\nİlginiz için teşekkürler!";
                break;
            default:
                throw new IllegalStateException("Beklenmeyen değer: " + status);
        }

        List<Intern> interns = internService.getInternsByStatus(status);
        Intern matchedIntern = interns.stream()
                .filter(intern -> intern.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (matchedIntern != null) {
            if (emailService.isEmailSent(matchedIntern.getMail(), status)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email has already been sent to this recipient for the given status");
            } else {
                emailService.sendEmail(matchedIntern.getMail(), subject, content, status);
                return ResponseEntity.ok("Email sent successfully");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No intern found with the specified name and status");
        }
    }

    @DeleteMapping("/delete")
    public void deleteEmails(@RequestBody List<String> recipients) {
        emailService.deleteEmails(recipients);
    }

    @GetMapping("/checkEmailSent")
    public boolean checkEmailSent(@RequestParam String recipient, @RequestParam String status) {
        return emailService.isEmailSent(recipient, status);
    }
}