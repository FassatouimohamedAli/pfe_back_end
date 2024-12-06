package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Payment;
import com.pfe.jwt.entity.Risk;
import com.pfe.jwt.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService ;


    @GetMapping("/searchPaymentDebiteur")
    @PreAuthorize("hasRole('Recouvrement')")
    public ResponseEntity<Object> searchDebiteur(@RequestParam Long id) {
        List<Payment> payments = paymentService.findPaymentsByDebiteurNumero(id);
        if (!payments.isEmpty()) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("payments debiteur  not found");
        }
    }

    @GetMapping("/findAllpayment")
    public ResponseEntity<Object> findall() {
        List<Payment> payments = paymentService.listePayment();
        if (!payments.isEmpty()) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" liste des payments   not found");
        }
    }
}
