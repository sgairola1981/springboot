package com.gairola.gairolaapp.controller;

import com.gairola.gairolaapp.entity.Customer;
import com.gairola.gairolaapp.repository.CustomerRepository;
import com.gairola.gairolaapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String index() {
        return "/customer.html";
    }

    @GetMapping("/details")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Customer customerInstance = customerService.getCustomerById(id);

        model.addAttribute("customerInstance", customerInstance);

        return "/editcustomer.html";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") long id,
            @Valid @ModelAttribute("customerInstance") Customer customerInstance,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes atts) {
        customerInstance = customerService.getCustomerById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("customer", customerInstance);
        return findPaginated(1, "firstName", "asc", model);

    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customerInstance", new Customer());
        return "createcustomer.html";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("customerInstance") Customer customerInstance,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes atts) {

        customerService.saveCustomer(customerInstance);
        return findPaginated(1, "firstName", "asc", model);

    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes atts) {
        this.customerService.deleteCustomerById(id);
        atts.addFlashAttribute("message", "Customer deleted.");

        return "redirect:/customer/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 5;

        Page<Customer> page = customerService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Customer> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "customer";
    }

}