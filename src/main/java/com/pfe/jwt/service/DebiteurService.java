package com.pfe.jwt.service;

import com.pfe.jwt.entity.*;
import com.pfe.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebiteurService {

    @Autowired
    private DebiteurRepo debiteurRepo;
    @Autowired
    private TypeidentiteRepo typeidentiteRepo;
    @Autowired
    private ClientRepo clientRepo;

@Autowired
private CinRepo cinRepo ;
@Autowired
private PasseportRepo passeportRepo ;



    public ResponseEntity<String> addNewDebiteur(Debiteur debiteur, Client client, TypeIdentite typeIdentite) {
        try {

            if (client == null) {
                throw new IllegalArgumentException("Client cannot be null");
            }

            // Check if the client is already registered in the database
            if (client.getId() != null && clientRepo.findById(client.getId()).isPresent()) {
                throw new IllegalArgumentException("Client with ID " + client.getId() + " already exists");
            }

           /* List<Client> existingClients = clientDao.findByLastName(client.getLastName());
            if (!existingClients.isEmpty()) {
                return ResponseEntity.badRequest().body("Client with the same first name already exists");
            }*/

            // Save the client
            clientRepo.save(client);

            // Verify if the piece number already exists
            List<TypeIdentite> existingTypeIdentites = typeidentiteRepo.findBynumPiece(typeIdentite.getNumPiece());
            if (!existingTypeIdentites.isEmpty()) {
                return ResponseEntity.badRequest().body("TypeIdentite with the same numero already exists");
            }



            // Set the client for the TypeIdentite
            typeIdentite.setClient(client);

            if (typeIdentite instanceof Cin) {
                Cin cin = (Cin) typeIdentite;
                cinRepo.save(cin);
            } else if (typeIdentite instanceof Passeport) {
                Passeport passeport = (Passeport) typeIdentite;
                passeportRepo.save(passeport);
            }

            // Set the client for the Debiteur
            debiteur.setClient(client);

            // Save the TypeIdentite
            typeidentiteRepo.save(typeIdentite);



            // Save the Debiteur
            debiteurRepo.save(debiteur);

            return ResponseEntity.ok("Debiteur added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid arguments: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add debiteur: " + e.getMessage());
        }
    }




   public Optional<Debiteur> findDebiteurById(Long debiteurId) {
return debiteurRepo.findById(debiteurId);
    }
public List<Debiteur> FindAllDebiteur(){return debiteurRepo.findAll() ;}

}