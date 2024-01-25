package sid.org.customerservice.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sid.org.customerservice.dtos.CustomerDTO;
import sid.org.customerservice.exception.CinAlreadyExistException;
import sid.org.customerservice.exception.CustomerNotFoundException;
import sid.org.customerservice.exception.EmailAlreadyExistException;
import sid.org.customerservice.exception.PhoneAlreadyExistException;
import sid.org.customerservice.sevices.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {


    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public CustomerDTO save(@RequestBody CustomerDTO customerDTO) throws PhoneAlreadyExistException, CinAlreadyExistException, EmailAlreadyExistException {
      return customerService.save(customerDTO);
    }

    @PutMapping ("/update/{id}")
    public CustomerDTO update(@PathVariable Long id,@RequestBody CustomerDTO customerDTO) throws PhoneAlreadyExistException, CinAlreadyExistException, EmailAlreadyExistException, CustomerNotFoundException {
        return customerService.update(id,customerDTO);
    }
    @GetMapping("/getCustomer/{id}")
    public CustomerDTO getById(@PathVariable Long id) throws CustomerNotFoundException {
        return customerService.getById(id);
    }

    @GetMapping("/")
    public List<CustomerDTO> customers(){
        return customerService.getAll();
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return customerService.search("%"+keyword+"%");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        customerService.deleteById(id);
    }
}
