package com.pfe.jwt.service;

import com.pfe.jwt.repository.OperationRepo;
import com.pfe.jwt.repository.VirementRepo;
import com.pfe.jwt.entity.Operation;
import com.pfe.jwt.entity.Virement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VirementService {

    @Autowired
    private VirementRepo virementRepo;
    @Autowired
    private OperationRepo operationRepo;

    public Virement ajouterVirement(Long idOperation, Virement virement) {
        Optional<Operation> existingOperation = operationRepo.findById(idOperation);
        if (!existingOperation.isPresent()) {
            throw new RuntimeException("Operations Not Found");
        }
        if (existingOperation.get().getTypeOperation().equals("Cheque")) {
            throw new RuntimeException("Type of Payment Not Virement");
        }

        Operation operation = existingOperation.get();
        String nomBeneficiaire = operation.getNomBeneficier();
        //virement.setNomDestinataire(nomBeneficiaire);
        virement.setOperation(operation);
virement.setNomPropritaireVirement("Banque National Agricole");
        return virementRepo.save(virement);
    }
    public List<Virement> findVirement(){
        return virementRepo.findAll();
    }
}
