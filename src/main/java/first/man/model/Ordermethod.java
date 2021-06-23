package first.man.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ordermethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderid;
    private String ordermode;
    private String ordercode;
    private String ordertype;
    @ElementCollection
    private List<String> orderaccept;
    private String description;

}
