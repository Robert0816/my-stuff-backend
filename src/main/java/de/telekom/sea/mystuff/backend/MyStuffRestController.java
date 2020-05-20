package de.telekom.sea.mystuff.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/items")
@RestController
public class MyStuffRestController {

		@RequestMapping
		String test() {
			return "Hello World!!";
		}
	
	
}
