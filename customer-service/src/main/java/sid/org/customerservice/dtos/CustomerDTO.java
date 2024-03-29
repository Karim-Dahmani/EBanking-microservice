package sid.org.customerservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CustomerDTO {

    private String name;
    private String email;
    private String cin;
    private String phone;
}
