package com.RMDSpring.springrest.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Participant {
	
		String ParticipantName;
		private long SocialSecurityNumber;
		private BigDecimal accountBalance;
		private LocalDate dateOfBirth;
		private transient int age;
		private AccountStatus accountStatus;
		private BigDecimal rmdTaxPercentage;
		
		public Participant(String participantName, long socialSecurityNumber, BigDecimal accountBalance,
				LocalDate dateOfBirth, int age, AccountStatus  accountStatus, BigDecimal rmdTaxPercentage) {
			super();
			ParticipantName = participantName;
			SocialSecurityNumber = socialSecurityNumber;
			this.accountBalance = accountBalance;
			this.dateOfBirth = dateOfBirth;
			this.age = age;
			this.accountStatus = accountStatus;
			this.rmdTaxPercentage = rmdTaxPercentage;
		}

		public Participant() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getParticipantName() {
			return ParticipantName;
		}

		public void setParticipantName(String participantName) {
			ParticipantName = participantName;
		}

		public long getSocialSecurityNumber() {
			return SocialSecurityNumber;
		}

		public void setSocialSecurityNumber(long socialSecurityNumber) {
			SocialSecurityNumber = socialSecurityNumber;
		}

		public BigDecimal getAccountBalance() {
			return accountBalance;
		}

		public void setAccountBalance(BigDecimal accountBalance) {
			this.accountBalance = accountBalance;
		}

		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public AccountStatus getAccountStatus() {
			return accountStatus;
		}

		public void setAccountStatus(AccountStatus  accountStatus) {
			this.accountStatus = accountStatus;
		}

		public BigDecimal getRmdTaxPercentage() {
			return rmdTaxPercentage;
		}

		public void setRmdTaxPercentage(BigDecimal rmdTaxPercentage) {
			this.rmdTaxPercentage = rmdTaxPercentage;
		}

		@Override
		public String toString() {
			return "Participant [ParticipantName=" + ParticipantName + ", SocialSecurityNumber=" + SocialSecurityNumber
					+ ", accountBalance=" + accountBalance + ", dateOfBirth=" + dateOfBirth + ", age=" + age
					+ ", accountStatus=" + accountStatus + ", rmdTaxPercentage=" + rmdTaxPercentage
					+ ", getParticipantName()=" + getParticipantName() + ", getSocialSecurityNumber()="
					+ getSocialSecurityNumber() + ", getAccountBalance()=" + getAccountBalance() + ", getDateOfBirth()="
					+ getDateOfBirth() + ", getAge()=" + getAge() + ", getAccountStatus()=" + getAccountStatus()
					+ ", getRmdTaxPercentage()=" + getRmdTaxPercentage() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
		
		
		public enum AccountStatus {
		    TERMINATED,
		    ACTIVE,
		    INELIGIBLE
		}
	
		
}

