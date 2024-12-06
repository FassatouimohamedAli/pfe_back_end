package com.pfe.jwt.service;

import com.pfe.jwt.repository.*;
import com.pfe.jwt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class OperationRecouvrementService {

    @Autowired
    private OperationRecouvrementRepo operationRecouvrementRepo;
    @Autowired
    private ChequeClientRepo chequeClientRepo;

    @Autowired
    private VirementClientRepo virementClientRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private DebiteurRepo debiteurRepo;
    @Autowired
    private EchancierRepo echancierRepo;
@Autowired
private ArrangementRepo arrangementDao ;

@Autowired
private EchancierService echancierService ;
    public OperationRecouvrement createOperationRecouvrement(Long paymentId, String username) {
        Optional<Payment> existingPayment = paymentRepo.findById(paymentId);
        if (!existingPayment.isPresent()) {
            throw new IllegalArgumentException("Le paiement avec l'ID " + paymentId + " n'existe pas.");
        }
        Payment payment = existingPayment.get();

        // Create OperationRecouvrement
        OperationRecouvrement operationR = new OperationRecouvrement();
        operationR.setPayment(payment);
        operationR.setDateOperation(new Date());
        operationR.setTypeOperation("Recouvrement");
        operationR.setEtatOperation("En Cours");

        Optional<User> existingUser = userRepo.findById(username);
        if (!existingUser.isPresent()) {
            throw new IllegalArgumentException("L'utilisateur avec le nom d'utilisateur " + username + " n'existe pas.");
        }
        operationR.setUser(existingUser.get());

        return operationRecouvrementRepo.save(operationR);
    }

    public void validerOperationRecouvrement(Long operationId, String etat,String  username) {
        Optional<User> existingUser = userRepo.findById(username);
        if (!existingUser.isPresent()) {
            throw new IllegalArgumentException("User Not Found");
        }

        Optional<OperationRecouvrement> existingOperationRecouvrement = operationRecouvrementRepo.findById(operationId);
        if (!existingOperationRecouvrement.isPresent()) {
            throw new IllegalArgumentException("Operation Not Found");
        }

//        if(!existingOperationRecouvrement.get().getEtatOperation().equals("En Cours") || existingOperationRecouvrement.get().getEtatOperation().equals("Annuller") ){
//            throw new IllegalArgumentException("Operation  Not Found or  Etat is  Not 'En cours' ");
//        }

        existingOperationRecouvrement.get().setEtatOperation(etat);
        existingOperationRecouvrement.get().setUser(existingUser.get());

        OperationRecouvrement opR = existingOperationRecouvrement.get() ;

        if("valider".equalsIgnoreCase(etat)) {
            Debiteur deb = existingOperationRecouvrement.get().getPayment().getDebiteur();

            deb.calculeSoldeRecouvrmeent(opR);
            debiteurRepo.save(deb);

        }else if (opR.getEtatOperation().equalsIgnoreCase("annuller")){
            Debiteur deb = existingOperationRecouvrement.get().getPayment().getDebiteur();
            deb.AnnullerSoldeRecouvrmeent(opR);
            debiteurRepo.save(deb);
        }
        operationRecouvrementRepo.save(existingOperationRecouvrement.get());
    }




    public List<OperationRecouvrement> fetchAll() {
        return operationRecouvrementRepo.findAll();
    }


}
