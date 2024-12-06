package com.pfe.jwt.service;

import com.pfe.jwt.repository.ChequeRepo;
import com.pfe.jwt.repository.OperationRepo;
import com.pfe.jwt.entity.Cheque;
import com.pfe.jwt.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChequeService {

   @Autowired
   private ChequeRepo chequeRepo;
   @Autowired
    private OperationRepo operationRepo;


    public Cheque ajouterCheque(Long idOperation, Cheque cheque) {
        Optional<Operation> existingOperation = operationRepo.findById(idOperation);
        if (!existingOperation.isPresent()) {
            throw new RuntimeException("Operations Not Found");
        }
        if (existingOperation.get().getTypeOperation().equals("Virement")) {
            throw new RuntimeException("Type of Payment Not Cheque");
        }

        Operation operation = existingOperation.get();
        cheque.setNomPropritaire("Banque National Agricole");
        cheque.setOperation(operation);

        return chequeRepo.save(cheque);
    }

   public List<Cheque> findCheques(){
        return chequeRepo.findAll();
   }

}
