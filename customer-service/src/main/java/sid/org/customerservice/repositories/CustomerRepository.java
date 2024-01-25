package sid.org.customerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sid.org.customerservice.entities.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Boolean existsByCin(String cin);

    @Query("select c from Customer c where c.name like :kw")
    List<Customer> searchCustomer(@Param("kw") String keyword);
}
