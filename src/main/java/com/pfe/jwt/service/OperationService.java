package com.pfe.jwt.service;

import com.pfe.jwt.repository.DebiteurRepo;
import com.pfe.jwt.repository.OperationRepo;
import com.pfe.jwt.repository.RiskRepo;
import com.pfe.jwt.repository.UserRepo;
import com.pfe.jwt.entity.Debiteur;
import com.pfe.jwt.entity.Operation;
import com.pfe.jwt.entity.Risk;
import com.pfe.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private OperationRepo operationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DebiteurRepo debiteurRepo;

@Autowired
private RiskRepo riskRepo;

    public Operation addOperation(Operation operation,String  username,Long numero,long riskNumero) {
        // Verification of User
        Optional<User> existingUser = userRepo.findById(username);
        if (!existingUser.isPresent()) {
            throw new IllegalArgumentException("User Not Found");
        }
        operation.setUser(existingUser.get());
        Optional<Risk> existingRisk = riskRepo.findById(riskNumero);
        if (!existingRisk.isPresent()) {
            throw new IllegalArgumentException("Risk Not Found");
        }
        operation.setRisk(existingRisk.get());


        Optional<Debiteur> existingDebiteur = debiteurRepo.findById(numero);
        if (!existingDebiteur.isPresent()) {
            throw new IllegalArgumentException("Debiteur Not Found");
        }


        operation.setEtatOperation("En Cours");
        operation.setTypeOperation("Frais");

        // Condition of Type Payment
        if ("cheque".equalsIgnoreCase(operation.getTypePayment())) {
            // Si le type de paiement est un chèque,
            operation.setRib("****");
        } else if ("espece".equalsIgnoreCase(operation.getTypePayment())) {
            // Si le type de paiement est en espèces
            operation.setRib("****");
        }


        operation.setDateOperation(new Date());
        return operationRepo.save(operation);
    }

//   public List<Operation> findAllByDebiteur(long numero) {
//       return operationRepo.findAllByDebiteurNumero(numero);
//    }


    public void validerOperation(Long operationId, String etat,String  username) {
        Optional<User> existingUser = userRepo.findById(username);
        if (!existingUser.isPresent()) {
            throw new IllegalArgumentException("User Not Found");
        }

        Optional<Operation> existingOperation = operationRepo.findById(operationId);
        if (!existingOperation.isPresent() || !existingOperation.get().getEtatOperation().equals("En Cours")) {
            throw new IllegalArgumentException("Operation  Not Found or  Etat is  Not 'En cours' ");
        }
        existingOperation.get().setEtatOperation(etat);
        existingOperation.get().setUser(existingUser.get());


   if("valider".equalsIgnoreCase(etat)) {
       Operation operation = existingOperation.get();
       Risk riskdebiteurOp = existingOperation.get().getRisk();
       //fns calcule
       riskdebiteurOp.calculeRisk(operation);
   riskRepo.save(riskdebiteurOp);

   }

        operationRepo.save(existingOperation.get());
    }

    // op type frais
    public List<Operation> fetchOperations() {
        return operationRepo.findByTypeOperation("Frais");
    }
// les op Frais + recou

    public List<Operation> fetchAll() {
        return operationRepo.findAll();
    }
}
