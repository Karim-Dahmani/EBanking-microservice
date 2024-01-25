package sid.org.customerservice.sevices.Impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sid.org.customerservice.dtos.CustomerDTO;
import sid.org.customerservice.entities.Customer;
import sid.org.customerservice.exception.CinAlreadyExistException;
import sid.org.customerservice.exception.CustomerNotFoundException;
import sid.org.customerservice.exception.EmailAlreadyExistException;
import sid.org.customerservice.exception.PhoneAlreadyExistException;
import sid.org.customerservice.mappers.CustomerMapper;
import sid.org.customerservice.repositories.CustomerRepository;
import sid.org.customerservice.sevices.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j

public class CustomerServiceImpl implements CustomerService {

    private static final String ALREADY_EXIST = "' already exist.";
    private static final String CUSTOMER_WITH_EMAIL = "A customer with email :'";
    private static final String CUSTOMER_WITH_PHONE = "A customer with phone :'";
    private static final String CUSTOMER_WITH_CIN = "A customer with cin :'";


    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) throws CinAlreadyExistException, EmailAlreadyExistException ,PhoneAlreadyExistException{
        log.info("In save() :");
        CheckCinOrPhoneOrEmailBeforeSave(customerDTO);
        Customer customer = customerMapper.fromCustomerDTO(customerDTO);
        customer.setId(UUID.randomUUID().getMostSignificantBits()&Long.MAX_VALUE);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer saved successfully");
        return customerMapper.fromCustomer(savedCustomer) ;
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO customerDTO) throws PhoneAlreadyExistException,CinAlreadyExistException, EmailAlreadyExistException {
        log.info("In update() :");
        Customer customer = customerRepository.findById(id).orElse(null);

        checkCinOrEmailOrPhoneExistBeforeUpdate(customer , customerDTO);
        customerMapper.updateCustomerFromDTO(customer, customerDTO);
        Customer updateCustomer = customerRepository.save(customer);
        log.info("Customer saved successfully");
        return customerMapper.fromCustomer(updateCustomer);
    }

    @Override
    public CustomerDTO getById(Long id) throws CustomerNotFoundException {
        log.info("In getById() :");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id :"+id+" Not found"));
        log.info("customer with id = '"+id+"' found successfully.");
        return customerMapper.fromCustomer(customer);

    }


    @Override
    public List<CustomerDTO> getAll() {
     //   customerRepository.findAll(PageRequest.of(page, size));
        log.info("In getAll() :");

        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> customerMapper.fromCustomer(customer))
                .collect(Collectors.toList());
        log.info("All Customers");
        return customerDTOS;
    }

    @Override
    public List<CustomerDTO> search(String keyword) {
        List<Customer> customers=customerRepository.searchCustomer(keyword);
        List<CustomerDTO> customerDTOS = customers.stream().map(cust -> customerMapper.fromCustomer(cust)).collect(Collectors.toList());
        return customerDTOS;
    }


    @Override
    public void deleteById(Long id) {
        log.info("In deleteById() :");
        customerRepository.deleteById(id);
        log.info("customer deleted");
    }


    public void CheckCinOrPhoneOrEmailBeforeSave(@NotNull CustomerDTO customerDTO) throws EmailAlreadyExistException, CinAlreadyExistException, PhoneAlreadyExistException {
    CheckEmail(customerDTO.getEmail());
    CheckCin(customerDTO.getCin());
    CheckPhone(customerDTO.getPhone());
    }

    private void checkCinOrEmailOrPhoneExistBeforeUpdate( Customer customer,  CustomerDTO customerDTO) throws EmailAlreadyExistException,CinAlreadyExistException,PhoneAlreadyExistException {
        if(!customer.getEmail().equals(customerDTO.getEmail())){
            CheckEmail(customerDTO.getEmail());
        }
        if(!customer.getCin().equals(customerDTO.getCin())){
            CheckCin(customerDTO.getCin());
        }
        if(!customer.getPhone().equals(customerDTO.getPhone())){
            CheckPhone(customerDTO.getPhone());
        }
    }
    private void CheckEmail(String email) throws EmailAlreadyExistException {

        if (Boolean.TRUE.equals(customerRepository.existsByEmail(email))) {
            throw new EmailAlreadyExistException(CUSTOMER_WITH_EMAIL + email + ALREADY_EXIST);
        }
    }

    private void CheckCin(String cin) throws CinAlreadyExistException {

        if (Boolean.TRUE.equals(customerRepository.existsByCin(cin))) {
            throw new CinAlreadyExistException(CUSTOMER_WITH_CIN + cin + ALREADY_EXIST);
        }
    }

    private void CheckPhone(String phone) throws PhoneAlreadyExistException {

        if (Boolean.TRUE.equals(customerRepository.existsByPhone(phone))) {
            throw new PhoneAlreadyExistException(CUSTOMER_WITH_PHONE + phone + ALREADY_EXIST);
        }
    }
}
