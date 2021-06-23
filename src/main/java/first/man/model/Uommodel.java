package first.man.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Uommodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uomid;
    private String uomtype;
    private String uommodel;
    private String description;
}
