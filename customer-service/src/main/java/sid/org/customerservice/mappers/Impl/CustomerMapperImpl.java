package sid.org.customerservice.mappers.Impl;

import org.antlr.v4.runtime.misc.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sid.org.customerservice.dtos.CustomerDTO;
import sid.org.customerservice.entities.Customer;
import sid.org.customerservice.mappers.CustomerMapper;

import java.util.List;

@Service

public class CustomerMapperImpl implements CustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();


    public CustomerMapperImpl() {
        super();
    }

    @Override
    public CustomerDTO fromCustomer(Customer customer) {
//        return new CustomerDTO(
//                customer.getId(),
//                customer.getName(),
//                customer.getEmail()
//        );
      return   modelMapper.map(customer,CustomerDTO.class);
    }

    @Override
    public void updateCustomerFromDTO( Customer customer,  CustomerDTO customerDTO){
       modelMapper.map(customerDTO,customer);
    }
    @Override
    public Customer fromCustomerDTO(CustomerDTO customerDTO) {

        return   modelMapper.map(customerDTO,Customer.class);
    }

    @Override
    public List<CustomerDTO> fromListOfCustomers(List<Customer> customers) {
        return customers.stream().map(this::fromCustomer).toList();
    }
}
