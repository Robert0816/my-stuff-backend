package de.telekom.sea.mystuff.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.telekom.sea.mystuff.backend.entities.Item;
import de.telekom.sea.mystuff.backend.repo.MystuffRepo;

@RequestMapping("/api/v1/items")
@RestController
public class MyStuffRestController {

//		@RequestMapping
//		String test() {
//			return "Hello World!!";
//		}

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

}
