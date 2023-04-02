package com.example.shopjava6.restController;



import com.example.shopjava6.ripository.OrderRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import com.example.shopjava6.entity.Order;
import com.example.shopjava6.service.OrderService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	@PostMapping()
	public Order create(@RequestBody JsonNode orderData) {
			return orderService.create(orderData);
	}

	@GetMapping()
	public List<Order> getAll(){
		Sort sort = Sort.by(Sort.Direction.DESC,"id");
		return orderRepository.findAll(sort);
	}



//	@GetMapping("{id}")
//	public Order findbyId(@PathVariable("id") long id) {
//
//		return orderRepository.findById(id).get();
//	}

//	@PutMapping("{id}")
//	public Order update(@PathVariable("id") long id, @RequestBody Order order) {
//		return orderRepository.save(order);
//	}

//	@GetMapping("{from}/{to}/{status}")
//	public List<Order> findByDate(@PathVariable("from") String from,@PathVariable("to") String to,
//								  @PathVariable("status") String status){
//		if(status.equals("all"))
//			status = "%%";
//		return orderRepository.findByDate(from,to,status);
//	}
}
