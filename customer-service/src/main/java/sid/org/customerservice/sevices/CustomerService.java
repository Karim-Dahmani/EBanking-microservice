package sid.org.customerservice.sevices;


import sid.org.customerservice.dtos.CustomerDTO;
import sid.org.customerservice.exception.CinAlreadyExistException;
import sid.org.customerservice.exception.CustomerNotFoundException;
import sid.org.customerservice.exception.EmailAlreadyExistException;
import sid.org.customerservice.exception.PhoneAlreadyExistException;

import java.util.List;

public interface CustomerService {

    CustomerDTO save(CustomerDTO customerDTO) throws CinAlreadyExistException,EmailAlreadyExistException, PhoneAlreadyExistException;
    CustomerDTO update(Long id, CustomerDTO customerDTO) throws  PhoneAlreadyExistException,CinAlreadyExistException,EmailAlreadyExistException;
    CustomerDTO getById(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> getAll();
    List<CustomerDTO> search(String keyword);
    void deleteById(Long id);



}
