package ma.enset.productmangmentspring.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder @Getter @Setter @ToString
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String username;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

}
