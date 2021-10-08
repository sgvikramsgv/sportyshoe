package com.sportyshoe.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sportyshoe.dto.ProductDTO;
import com.sportyshoe.model.Category;
import com.sportyshoe.model.Order;
import com.sportyshoe.model.Product;
import com.sportyshoe.service.CategoryService;
import com.sportyshoe.service.OrderService;
import com.sportyshoe.service.ProductService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/admin")
	public String adminHome() {
		
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		
		categoryService.removeCatById(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		
		Optional<Category> category = categoryService.getCategoryById(id);
		
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else {
			return "404";
		}
		
	}
	
	
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model) {
		
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String postCatAdd(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName) throws IOException {
		
		Product tempProd = new Product();
		tempProd.setName(productDTO.getName());
		tempProd.setPrice(productDTO.getPrice());
		
		tempProd.setDescription(productDTO.getDescription());
		tempProd.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		
		
		String imageUUID;
		
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = imgName;
		}
		tempProd.setImageName(imageUUID);
		productService.addProduct(tempProd);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		
		productService.removeProductById(id);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable long id, Model model) {
		
		Product tempProduct = productService.getProductById(id).get();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(tempProduct.getId());
		productDTO.setName(tempProduct.getName());
		productDTO.setDescription(tempProduct.getDescription());
		productDTO.setPrice(tempProduct.getPrice());
		productDTO.setCategoryId(tempProduct.getCategory().getId());
		productDTO.setImageName(tempProduct.getImageName());
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		
		return "productsAdd";
	}
	
	@GetMapping("/admin/orders")
	public String ListOrders(Model model) {
		
		model.addAttribute("orderList", orderService.getAllOrders());
		return "orderList";
	}
}
