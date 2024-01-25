package sid.org.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sid.org.customerservice.entities.Customer;
import sid.org.customerservice.repositories.CustomerRepository;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {
@Autowired
CustomerRepository customerRepository;
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	//@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {

			customerRepository.saveAll(
					List.of(
							Customer.builder()
									.name("amine")
									.email("amine@gmail.com")
									.phone("0600537702")
									.cin("F4455")
									.build(),
							Customer.builder()
									.name("mohammed")
									.email("mohammed@gmail.com")
									.phone("0607777702")
									.cin("D04755")
									.build(),
							Customer.builder()
									.name("karim")
									.email("karim@gmail.com")
									.phone("0670054872")
									.cin("A5555")
									.build()
					)
			);

			customerRepository.findAll().forEach(customer ->
			{
				System.out.println(customer);
			});
		};
	}
}
