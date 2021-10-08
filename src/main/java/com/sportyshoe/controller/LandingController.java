package com.sportyshoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sportyshoe.global.GlobalData;
import com.sportyshoe.service.CategoryService;
import com.sportyshoe.service.ProductService;

@Controller
public class LandingController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping({"/", "/home"})
	public String landingHome(Model model) {
		
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop")
	public String shoppingPage(Model model) {
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String filterByCategory(Model model, @PathVariable int id) {
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getProductsByID(id));
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String getProductDetails(Model model, @PathVariable int id) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("product", productService.getProductById(id).get());
		return "viewProduct";
	}
}
