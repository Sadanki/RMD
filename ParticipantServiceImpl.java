package com.RMDSpring.springrest.Services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RMDSpring.springrest.entities.Participant;
import com.RMDSpring.springrest.entities.Participant.AccountStatus;

@Service
  public class ParticipantServiceImpl implements ParticipantService {
	
	@Autowired
	private com.RMDSpring.springrest.myrmdproject.DAO.RMDDao RMDDao;
	
		List<Participant> list;
	
	 {

		list= new ArrayList<>();
		
		list.add(new Participant("John", 123456789, BigDecimal.valueOf(10000),
                LocalDate.of(1945, 5, 15), 79, AccountStatus.ACTIVE, BigDecimal.valueOf(10)));
		
		list.add(new Participant("Alice", 987654321, BigDecimal.valueOf(20000),
                LocalDate.of(1949, 8, 25), 75, AccountStatus.TERMINATED, BigDecimal.valueOf(15)));
		
		}
	
	@Override
	public List<Participant> getParticipants() {
		return list;
	}

	@Override
	public Participant getParticipant(long SocialSecurityNumber) {
		return list.stream()  // Change ParticipantList to list
	            .filter(participant -> participant.getSocialSecurityNumber() == SocialSecurityNumber)
	            .findFirst()
	            .orElse(null);
		
	}
	
	@Override
	public Participant addParticipant(Participant Participant) {
		list.add(Participant);
		return Participant;
	}
	
	@Override
	public Participant updateParticipant(long SocialSecurityNumber, Participant updatedParticipant) {
	    for (int i = 0; i < list.size(); i++) {
	        Participant Participant = list.get(i);
	        if (Participant.getSocialSecurityNumber() == SocialSecurityNumber) {
	            // Update the participant's information
	        	Participant.setAccountBalance(updatedParticipant.getAccountBalance());
	        	Participant.setDateOfBirth(updatedParticipant.getDateOfBirth());
	        	Participant.setAccountStatus(updatedParticipant.getAccountStatus());
	        	Participant.setRmdTaxPercentage(updatedParticipant.getRmdTaxPercentage());
	        	Participant.setParticipantName(updatedParticipant.getParticipantName());
	            list.set(i, Participant); // Update the list with the modified participant
	            return Participant;
	        }
	    }
	    return null; // Participant not found
	}
	
	@Override
	public void deleteParticipant(long SocialSecurityNumber) {
	    for (Iterator<Participant> iterator = list.iterator(); iterator.hasNext();) {
	        Participant participant = iterator.next();
	        if (participant.getSocialSecurityNumber() == SocialSecurityNumber) {
	            iterator.remove();
	            return; // Participant deleted, exit the method
	        }
	    }
	    // Participant not found, no action needed
	}

	
	 private final Map<Integer, BigDecimal> irsTable = new HashMap<>();
	 	
	 public ParticipantServiceImpl() {
	        // Initialize IRS distribution table data
		 irsTable.put(72, BigDecimal.valueOf(27.4));
		 irsTable.put(73, BigDecimal.valueOf(26.5));
		 irsTable.put(74, BigDecimal.valueOf(25.6));
		 irsTable.put(75, BigDecimal.valueOf(24.7));
		 irsTable.put(76, BigDecimal.valueOf(23.8));
		 irsTable.put(77, BigDecimal.valueOf(22.9));
		 irsTable.put(78, BigDecimal.valueOf(22.0));
		 irsTable.put(79, BigDecimal.valueOf(21.2));
		 irsTable.put(80, BigDecimal.valueOf(20.3));
		 irsTable.put(81, BigDecimal.valueOf(19.5));
		 irsTable.put(82, BigDecimal.valueOf(18.7));
		 irsTable.put(83, BigDecimal.valueOf(17.9));
		 irsTable.put(84, BigDecimal.valueOf(17.1));
		 irsTable.put(85, BigDecimal.valueOf(16.3));
		 irsTable.put(86, BigDecimal.valueOf(15.5));
		 irsTable.put(87, BigDecimal.valueOf(14.8));
		 irsTable.put(88, BigDecimal.valueOf(14.1));
		 irsTable.put(89, BigDecimal.valueOf(13.4));
		 irsTable.put(90, BigDecimal.valueOf(12.7));
		 irsTable.put(91, BigDecimal.valueOf(12.0));
		 irsTable.put(92, BigDecimal.valueOf(11.4));
		 irsTable.put(93, BigDecimal.valueOf(10.8));
		 irsTable.put(94, BigDecimal.valueOf(10.2));
		 irsTable.put(95, BigDecimal.valueOf(9.6));
		 irsTable.put(96, BigDecimal.valueOf(9.1));
		 irsTable.put(97, BigDecimal.valueOf(8.6));
		 irsTable.put(98, BigDecimal.valueOf(8.1));
		 irsTable.put(99, BigDecimal.valueOf(7.6));
		 irsTable.put(100, BigDecimal.valueOf(7.1));
		 irsTable.put(101, BigDecimal.valueOf(6.7));
		 irsTable.put(102, BigDecimal.valueOf(6.3));
		 irsTable.put(103, BigDecimal.valueOf(5.9));
		 irsTable.put(104, BigDecimal.valueOf(5.5));
		 irsTable.put(105, BigDecimal.valueOf(5.2));
		 irsTable.put(106, BigDecimal.valueOf(4.9));
		 irsTable.put(107, BigDecimal.valueOf(4.5));
		 irsTable.put(108, BigDecimal.valueOf(4.2));
		 irsTable.put(109, BigDecimal.valueOf(3.9));
		 irsTable.put(110, BigDecimal.valueOf(3.7));
		 irsTable.put(111, BigDecimal.valueOf(3.4));
		 irsTable.put(112, BigDecimal.valueOf(3.1));
		 irsTable.put(113, BigDecimal.valueOf(2.9));
		 irsTable.put(114, BigDecimal.valueOf(2.6));
		 irsTable.put(115, BigDecimal.valueOf(2.4));
		 irsTable.put(116, BigDecimal.valueOf(2.1));
		 irsTable.put(117, BigDecimal.valueOf(1.9));
		 irsTable.put(118, BigDecimal.valueOf(1.8));
		 irsTable.put(119, BigDecimal.valueOf(1.6));
		 irsTable.put(120, BigDecimal.valueOf(1.5));
	 }
	 
	 @Override
	    public BigDecimal calculateRMD(long SocialSecurityNumber) {
	        Participant participant = getParticipant(SocialSecurityNumber);
	        if (participant == null) {
	            return null; // Participant not found
	        }
	        
	        int age = calculateAge(participant.getDateOfBirth());
	        if (age < 73) {
	            // Participant does not qualify for RMD
	            System.out.println("Participant does not qualify for RMD as Age is: " + age);
	            return null;
	        }
	        
	        BigDecimal distributionPeriod = irsTable.get(age);
	        if (distributionPeriod == null) {
	            // Handle case where distribution period is not available for the given age
	            System.out.println("Distribution period is not available for age: " + age);
	            return null;
	        }
	     // Calculate RMD based on account balance and distribution period
	        BigDecimal rmdAmount = participant.getAccountBalance().divide(distributionPeriod, 2, BigDecimal.ROUND_HALF_UP);
	        return rmdAmount;
	    }
	        
	        private int calculateAge(LocalDate dateOfBirth) {
	            LocalDate currentDate = LocalDate.now();
	            int age = currentDate.getYear() - dateOfBirth.getYear();
	            if (currentDate.getMonthValue() < dateOfBirth.getMonthValue()
	                    || (currentDate.getMonthValue() == dateOfBirth.getMonthValue()
	                    && currentDate.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
	                age--; // Adjust age if birthday hasn't occurred yet in the current year
	            }
	            return age;
	        }

			@Override
			public void submitRMD(long socialSecurityNumber, BigDecimal rmdAmount) {
				// TODO Auto-generated method stub
				
			}

			/*@Override
			public void submitRMD(long socialSecurityNumber, BigDecimal rmdAmount) {
				Participant participant = getParticipant(socialSecurityNumber);
			    if (participant != null) {
			        // Update participant's RMD amount
			        participant.setRmdAmount(rmdAmount);
			        /// Set RMD submission date
			        participant.setRmdSubmissionDate(LocalDate.now());

			        // Update account balance
			        participant.getAccount().deduct(rmdAmount);
			    } else {
			        // Handle case where participant is not found
			        throw new IllegalArgumentException("Participant not found with SSN: " + socialSecurityNumber);
			    }
				
			}*/
	   

}


