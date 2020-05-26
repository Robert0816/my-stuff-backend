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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.telekom.sea.mystuff.backend.entities.Item;
import de.telekom.sea.mystuff.backend.repo.MystuffRepo;

@RequestMapping("/api/v1/items/")
@RestController
public class MyStuffRestController {

	private MystuffRepo mystuffRepo;

	@Autowired
	public MyStuffRestController(MystuffRepo mystuffRepo) {
		super();
		this.mystuffRepo = mystuffRepo;
	}

	@GetMapping
	public List<Item> getAllItems() {
		return this.mystuffRepo.findAll();
	}

	@GetMapping("{id}")
	public Item get(@PathVariable Long id) {
		return mystuffRepo.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Item createItem(@Validated @RequestBody Item newItem) {
		return mystuffRepo.save(newItem);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws RelationNotFoundException {
		try {
			mystuffRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RelationNotFoundException();
		}
	}

	@PutMapping("{id}")
	public Item updateItem(@PathVariable(value = "id") Long id, @Validated @RequestBody Item newItem)
			throws RelationNotFoundException {
		Item item = mystuffRepo.findById(id)
				.orElseThrow(() -> new RelationNotFoundException("Id not found for this id :: " + id));

		item.setName(newItem.getName());
		item.setDescription(newItem.getDescription());
		item.setAmount(newItem.getAmount());
		item.setLocation(newItem.getLocation());
		item.setLastUsed(newItem.getLastUsed());
		final Item updatedItem = mystuffRepo.save(item);
		return updatedItem;
	}

}
