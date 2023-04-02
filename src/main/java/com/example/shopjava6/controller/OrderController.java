package com.example.shopjava6.controller;


import com.example.shopjava6.entity.Order;
import com.example.shopjava6.ripository.OrderDetailRepository;
import com.example.shopjava6.ripository.OrderRepository;
import com.example.shopjava6.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("order/checkout")
    public String checkout() {

        return "order/checkout";
    }

   @RequestMapping("order/list")
    public String list(Model model, HttpServletRequest request) {
       String username = request.getRemoteUser();
       model.addAttribute("orders",orderService.findByUser(username));
        return "order/list";
   }

   @RequestMapping("order/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {

       model.addAttribute("order",orderService.findById(id));
        return "order/detail";
    }
   


}

