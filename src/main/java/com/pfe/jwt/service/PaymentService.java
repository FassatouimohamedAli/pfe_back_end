package com.pfe.jwt.service;

import com.pfe.jwt.repository.DebiteurRepo;
import com.pfe.jwt.repository.PaymentRepo;
import com.pfe.jwt.entity.Debiteur;
import com.pfe.jwt.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {


    @Autowired
    private PaymentRepo paymentRepo;
@Autowired
private DebiteurRepo debiteurRepo;

    public List<Payment> findPaymentsByDebiteurNumero(Long numero) {
        Optional<Debiteur> debiteur = debiteurRepo.findById(numero);
        if (!debiteur.isPresent()) {
            throw new IllegalArgumentException("Debiteur with numero " + numero + " does not exist.");
        }
        Debiteur d = debiteur.get();
        return paymentRepo.findAllByDebiteurNumero(d.getNumero());
    }


    public List<Payment> listePayment(){
        return paymentRepo.findAll() ;
    }

}
