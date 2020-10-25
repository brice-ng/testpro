package com.pro.repositorie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pro.model.Users;


@Repository
public interface ReadFileRepository extends CrudRepository<Users, Long> {

	
	
	
}
