package first.man.model;

import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Purchaseorder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //one to one with grna
    private String ordercode;
    private String referencenumber;
    private String qualitycheck;
    private String defaultstatus;
    private String description;
    @ManyToOne
    @JoinColumn(name = "shipmentpurchase")
    private Shipmentmodel shipmentmodel;
    @ManyToOne
    @JoinColumn(name = "Userpurchase")
    private User user;


}
