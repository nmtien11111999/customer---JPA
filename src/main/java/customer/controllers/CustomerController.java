package customer.controllers;


import customer.models.Customer;
import customer.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "/list")
    public String getAllCustomer(ModelMap modelMap) {
        modelMap.addAttribute("customers", customerService.findAll());
        return "list";
    }

    @GetMapping(value = "/view/{id}")
    public String addCustomer(@PathVariable int id, ModelMap modelMap) {
        modelMap.addAttribute("customer", customerService.findById(id));
        return "view";
    }

    @GetMapping(value = "/create")
    public String createCustomer(ModelMap modelMap) {
        modelMap.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping(value = "/create")
    public String createCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect: /admin/list";
    }

    @GetMapping(value = "/edit/{id}")
    public String editCustomer(@PathVariable int id, ModelMap modelMap) {
        modelMap.addAttribute("customer", customerService.findById(id));
        return "edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect: /admin/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return "redirect: /admin/list";
    }


}
