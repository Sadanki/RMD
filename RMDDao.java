package com.RMDSpring.springrest.myrmdproject.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RMDSpring.springrest.entities.Participant;

public interface RMDDao extends JpaRepository<Participant, Long> {

}
