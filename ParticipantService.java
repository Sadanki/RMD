package com.RMDSpring.springrest.Services;

import java.math.BigDecimal;
import java.util.List;

import com.RMDSpring.springrest.entities.Participant;

public interface ParticipantService {
	
	public List<Participant> getParticipants(); 
	public Participant getParticipant(long SocialSecurityNumber);
	public Participant addParticipant(Participant Participant);
	public Participant updateParticipant(long SocialSecurityNumber, Participant updatedParticipant);
	public void deleteParticipant(long SocialSecurityNumber);
	 BigDecimal calculateRMD(long socialSecurityNumber);
	 void submitRMD(long socialSecurityNumber, BigDecimal rmdAmount);
	 	    }

