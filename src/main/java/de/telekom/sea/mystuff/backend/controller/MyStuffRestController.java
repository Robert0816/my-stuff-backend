package de.telekom.sea.mystuff.backend.controller;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.telekom.sea.mystuff.backend.entities.Item;
import de.telekom.sea.mystuff.backend.repo.MystuffRepo;

@RequestMapping("/api/v1")
@RestController
public class MyStuffRestController {

	@RequestMapping("/items")
	String test() {
		return "Hello World!!";
	}

	private MystuffRepo mystuffRepo;

	@Autowired
	public MyStuffRestController(MystuffRepo mystuffRepo) {
		super();
		this.mystuffRepo = mystuffRepo;
	}

	@GetMapping("/all")
	public List<Item> getAllItems() {
		return this.mystuffRepo.findAll();
	}

	@GetMapping("{id}")
	public Item get(@PathVariable Long id) {
		return mystuffRepo.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

	@PostMapping("/items")
	public Item createItem(@Validated @RequestBody Item newItem) {
		return mystuffRepo.save(newItem);
	}

	@DeleteMapping("Item/{id}")
	public void delete(@PathVariable Long id) throws RelationNotFoundException {
		try {
			mystuffRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RelationNotFoundException();
		}
	}

}
