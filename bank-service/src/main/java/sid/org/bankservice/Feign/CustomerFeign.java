package sid.org.bankservice.Feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sid.org.bankservice.dtos.CustomerDTO;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerFeign {
    @GetMapping("/bank/customers/get/{id}")
    CustomerDTO getById(@PathVariable String id);
}
