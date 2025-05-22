package ma.enset.productmangmentspring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @Size(min = 4, max = 20)
    private String name;
    @Min(0)
    private double price;
    @Min(0)
    private int quantity;
}
