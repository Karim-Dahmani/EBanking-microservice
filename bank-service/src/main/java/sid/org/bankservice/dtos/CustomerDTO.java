package sid.org.bankservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CustomerDTO {

    private Long Id;
    private String name;
    private String email;


}
