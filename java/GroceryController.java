package com.grocerymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.grocerymanagement.entity.Employee;
import com.grocerymanagement.entity.Product;
import com.grocerymanagement.service.EmployeeService;
import com.grocerymanagement.service.ProductService;




@Controller
public class GroceryController {
	
	private EmployeeService employeeService;
	private ProductService productService;
	
	public GroceryController(EmployeeService employeeService, ProductService productService) {
		super();
		this.employeeService = employeeService;
		this.productService = productService;
	}
	
	
	// dashboard that displays some business logic / product and employee overview, as well
	// as links to personell and inventory pages
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		
		model.addAttribute("manWage", employeeService.avgManagerWage());
		model.addAttribute("assocWage", employeeService.avgAssociateWage());
		model.addAttribute("pCount", employeeService.produceEmployeeCount());
		model.addAttribute("gCount", employeeService.groceryEmployeeCount());
		model.addAttribute("dCount", employeeService.dairyEmployeeCount());
		model.addAttribute("invValue", productService.totalInventoryValue());
		model.addAttribute("prodCost", productService.avgProductCost());
		model.addAttribute("sellPrice", productService.avgProductSellPrice());
		
		return "dashboard";
		
		
	}
	// employee dashboard
	@GetMapping("/personnel")
	public String employeeDash(Model model) {
			
		model.addAttribute("employees", employeeService.getAllEmployees());
			
		return "personnel";
		
		
	}
	
	// product dashboard
	@GetMapping("/inventory")
	public String productDash(Model model) {
		
		model.addAttribute("employees", employeeService.getAllEmployees());
			
		return "inventory";
		
		
	}
	
	// create new employee, pass into model, return add_employee view
	@GetMapping("/employee/add")
	public String addEmployeeForm(Model model) {
			
	
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "add_employee";
	}
	
	// bind form data to employee, and save
	// redirect to employee dashboard
	@PostMapping("/personnel")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		employeeService.saveEmployee(employee);
		return "redirect:/personnel";
		
	}
	
	@GetMapping("/employee/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "edit_employee";
		
	}
	
	// use Path Variable to grab id of existing emloyee. Use new form data to set new values of 
	// exisitng employee. Save new employee, and redirect to employee dashboard
	@PostMapping("/personnel/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
		
		// get employee id from database
		Employee existingEmployee = (Employee) employeeService.getEmployeeById(id);
		existingEmployee.setId(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setPosition(employee.getPosition());
		existingEmployee.setWage(employee.getWage());

		employeeService.updateEmployee(existingEmployee);
		return "redirect:/personnel";
	}
	
	// handler method to handle delete employee request
	@GetMapping("/personnel/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/personnel";
		
	}
	
	
	
	// create new product, pass into model, return add_product view
	@GetMapping("/product/add")
	public String addProductForm(Model model) {
			
	
		Product product = new Product();
		model.addAttribute("product", product);
		return "add_product";
	}
	
	// bind form data to product, and save
	// redirect to product dashboard
	@PostMapping("/inventory")
	public String saveProduct(@ModelAttribute("product") Product product) {
		
		productService.saveProduct(product);
		return "redirect:/inventory";
		
	}
	
	@GetMapping("/product/edit/{id}")
	public String editProductForm(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "edit_product";
		
	}
	
	// use Path Variable to grab id of existing product. Use new form data to set new values of 
	// exisitng product. Save new product, and redirect to product dashboard
	@PostMapping("/inventory/{id}")
	public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product, Model model) {
		
		// get product id from database
		Product existingProduct = (Product) productService.getProductById(id);
		existingProduct.setId(id);
		existingProduct.setName(product.getName());
		existingProduct.setDepartment(product.getDepartment());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setCost(product.getCost());
		existingProduct.setSellingPrice(product.getSellingPrice());
	
		productService.updateProduct(existingProduct);
		return "redirect:/inventory";
	}
	
	// handler method to handle delete product request
	@GetMapping("/inventory/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "redirect:/inventory";
		
	}

	

}
