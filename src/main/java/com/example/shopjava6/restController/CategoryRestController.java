package com.example.shopjava6.restController;

import java.util.List;

import com.example.shopjava6.entity.Category;
import com.example.shopjava6.ripository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/category")
public class CategoryRestController {

	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping()
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
}
