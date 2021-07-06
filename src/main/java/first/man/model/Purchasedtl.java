package first.man.model;

import com.itextpdf.text.pdf.security.PrivateKeySignature;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchasedtl
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Transient
    private Integer slno;
    private Integer qty;
    @ManyToOne
    @JoinColumn(name = "part_purchasedetails")
    private Part part;
    @ManyToOne
    @JoinColumn(name = "purchaseorder_purchasedtl")
    private Purchaseorder purchaseorder;
}
