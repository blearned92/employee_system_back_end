package com.infosys.spa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.spa.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserName(String userName);
}
