package com.pro.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.model.Choice;


@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

	
	
}
