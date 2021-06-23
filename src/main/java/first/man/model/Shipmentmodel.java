package first.man.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Shipmentmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shipid;
//    @NotNull
//    @NotEmpty
    private String shipmentmode;
//    @NotNull
//    @NotEmpty
    private String shipmentcode;
//    @NotNull
//    @NotEmpty
    private String shipmentenabled;
//    @NotNull
//    @NotEmpty
    private String shipmentgrade;
//    @NotNull
//    @NotEmpty
    private String description;


}
