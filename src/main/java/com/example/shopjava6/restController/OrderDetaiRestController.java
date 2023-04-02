package com.example.shopjava6.restController;

import java.util.List;

import com.example.shopjava6.entity.OrderDetail;
import com.example.shopjava6.ripository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderdetails")
public class OrderDetaiRestController {

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@GetMapping("{id}")
	public List<OrderDetail> getAll(@PathVariable("id") Long id){
		return orderDetailRepository.findByOrder(id);
	}
}
