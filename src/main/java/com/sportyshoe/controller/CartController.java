package com.sportyshoe.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sportyshoe.global.GlobalData;
import com.sportyshoe.model.CustomUserDetail;
import com.sportyshoe.model.Order;
import com.sportyshoe.model.Product;
import com.sportyshoe.model.User;
import com.sportyshoe.service.OrderService;
import com.sportyshoe.service.ProductService;

@Controller
public class CartController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}
	
	@GetMapping("/cart/removeItem/{id}")
	public String removeCartItem(@PathVariable int id) {
		GlobalData.cart.remove(id);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		
		
		return "checkout";
	}
	
	@PostMapping("/payNow")
	public String payNow(Model model, Authentication authentication) {
		
		Order order = new Order();
		String orderItem = "";
		String orderPrice = "";
		order.setUsername(authentication.getName());
		order.setOrderDate(LocalDateTime.now());
		
		for (Product cartItem : GlobalData.cart) {
			orderItem = orderItem + "\t"  + (cartItem.getName());
			orderPrice = orderPrice + "\t"  + (String.valueOf(cartItem.getPrice()));
		}
		order.setOrderItemName(orderItem);
		order.setOrderItemPrice(orderPrice);
		
		orderService.saveOrder(order);
		
		model.addAttribute("OrderData", GlobalData.cart);
		
		return "orderPlaced";
	}
}
