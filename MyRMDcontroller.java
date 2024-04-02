package com.RMDSpring.springrest.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RMDSpring.springrest.Services.ParticipantService;
import com.RMDSpring.springrest.entities.Participant;


@RestController
public class MyRMDcontroller {
	
	
	@Autowired
    private ParticipantService ParticipantService;
	
	@GetMapping("/home")
	public String home() {
		return "Unlock effortless RMD management with a single click - Your financial freedom starts here.";
		
	}
	
	//Get the list of participants
	@GetMapping("/Participants")
	public List<Participant> getParticipants()
		{
		
		return this.ParticipantService.getParticipants();
		
	}
	
	//Get single Participant
	@GetMapping("/Participant/{SocialSecurityNumber}")
    public Participant getParticipant(@PathVariable String SocialSecurityNumber) {
        return this.ParticipantService.getParticipant(Long.parseLong(SocialSecurityNumber));
    }
	
	
	//Add Participant
	@PostMapping("/Participants")
	public Participant addParticipant(@RequestBody  Participant Participant)
	{
		return this.ParticipantService.addParticipant(Participant);
			}
	
	
	@PutMapping("/Participants/{SocialSecurityNumber}")
	public ResponseEntity<Object> updateParticipant(@PathVariable String SocialSecurityNumber, @RequestBody Participant updatedParticipant) {
	    try {
	        long SSN = Long.parseLong(SocialSecurityNumber);
	        Participant existingParticipant = ParticipantService.getParticipant(SSN);
	        if (existingParticipant == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Update the participant's information
	        // Only allow updating of specific fields
	        existingParticipant.setAccountBalance(updatedParticipant.getAccountBalance());
	        existingParticipant.setDateOfBirth(updatedParticipant.getDateOfBirth());
	        existingParticipant.setAccountStatus(updatedParticipant.getAccountStatus());
	        existingParticipant.setRmdTaxPercentage(updatedParticipant.getRmdTaxPercentage());
	        existingParticipant.setParticipantName(updatedParticipant.getParticipantName());

	        // Call the updateParticipant method of your ParticipantService
	        Participant updated = ParticipantService.updateParticipant(SSN, existingParticipant);
	        if (updated == null) {
	            // Handle the case where the updateParticipant method returns null
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating participant");
	        }

	        // Return the updated participant if successful
	        return ResponseEntity.ok(updated);
	    } catch (NumberFormatException e) {
	        // Return bad request if the Social Security Number is invalid
	        return ResponseEntity.badRequest().body("Invalid Social Security Number");
	    }
	}
	
	
	@DeleteMapping("/Participant/{SocialSecurityNumber}")
	public ResponseEntity<Object> deleteParticipant(@PathVariable String SocialSecurityNumber) {
	    try {
	        long ssn = Long.parseLong(SocialSecurityNumber);
	        Participant existingParticipant = ParticipantService.getParticipant(ssn);
	        if (existingParticipant == null) {
	            return ResponseEntity.notFound().build();
	        }
	        ParticipantService.deleteParticipant(ssn);
	        return ResponseEntity.ok("Participant deleted successfully");
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("Invalid Social Security Number");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting participant");
	    }
	}
	
	
	@GetMapping("/calculateRMD/{socialSecurityNumber}")
	public ResponseEntity<Object> calculateRMD(@PathVariable String socialSecurityNumber) {
	    try {
	        long ssn = Long.parseLong(socialSecurityNumber);
	        BigDecimal rmdAmount = ParticipantService.calculateRMD(ssn);
	        if (rmdAmount != null) {
	            return ResponseEntity.ok("RMD Amount for SSN " + socialSecurityNumber + ": $" + rmdAmount);
	        } else {
	            return ResponseEntity.badRequest().body("Participant does not qualify for RMD");
	        }
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("Invalid Social Security Number");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error calculating RMD for participant");
	    }
	}
	
	@PostMapping("/submitRMD")
	public ResponseEntity<Object> submitRMD(@RequestParam("socialSecurityNumber") String socialSecurityNumber,
	                                         @RequestParam("rmdAmount") BigDecimal rmdAmount) {
	    try {
	        // Convert socialSecurityNumber to long if necessary
	        long ssn = Long.parseLong(socialSecurityNumber);
	        
	        // Call the ParticipantService to submit the RMD
	        ParticipantService.submitRMD(ssn, rmdAmount);
	        
	        return ResponseEntity.ok("RMD submitted successfully for SSN: " + socialSecurityNumber);
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("Invalid Social Security Number");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error submitting RMD for participant: " + e.getMessage());
	    }
	}
	
	@PostMapping("/submitRMD/{SocialSecurityNumber}")
	public ResponseEntity<Object> submitRMD(@PathVariable String SocialSecurityNumber) {
	    try {
	        long ssn = Long.parseLong(SocialSecurityNumber);
	        BigDecimal rmdAmount = ParticipantService.calculateRMD(ssn);
	        if (rmdAmount != null) {
	            // Here you can perform any action necessary to submit the RMD
	            // For example, update the participant's account balance with the RMD amount
	            return ResponseEntity.ok("RMD submitted successfully for SSN " + SocialSecurityNumber + ": $" + rmdAmount);
	        } else {
	            return ResponseEntity.badRequest().body("RMD calculation failed for participant");
	        }
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body("Invalid Social Security Number");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error submitting RMD for participant");
	    }
	}
}


