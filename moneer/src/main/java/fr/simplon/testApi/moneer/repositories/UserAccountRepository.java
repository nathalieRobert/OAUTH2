package fr.simplon.testApi.moneer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.simplon.testApi.moneer.entities.UserAccount;



public interface UserAccountRepository
	extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);
}
