package first.man.model;

import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Saleorder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int saleid;
    //	@ManyToOne
//	@JoinColumn(name = "sale_ordercode")
    private String ordercode;
    @ManyToOne
    @JoinColumn(name = "sale_shipmentcode")
    private Shipmentmodel shipmentcode;
    @ManyToOne
    @JoinColumn(name = "sale_user")
    private User user;
    private String referenceno;
    private String stockmode;
    private String stocksource;
    private String defaultstatus;
    private String description;
}
