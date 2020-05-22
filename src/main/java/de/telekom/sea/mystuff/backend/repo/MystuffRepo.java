package de.telekom.sea.mystuff.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import de.telekom.sea.mystuff.backend.entities.Item;



public interface MystuffRepo extends JpaRepository<Item, Long> {
	

}
