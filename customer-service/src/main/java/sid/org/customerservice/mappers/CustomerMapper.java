package sid.org.customerservice.mappers;

import org.antlr.v4.runtime.misc.NotNull;
import sid.org.customerservice.dtos.CustomerDTO;
import sid.org.customerservice.entities.Customer;

import java.util.List;

public interface CustomerMapper {
    CustomerDTO fromCustomer(Customer customer);

    void updateCustomerFromDTO(@NotNull Customer customer, @NotNull CustomerDTO customerDTO);

    Customer fromCustomerDTO(CustomerDTO customerDTO);
    List<CustomerDTO> fromListOfCustomers(@NotNull List<Customer> customers);


}
