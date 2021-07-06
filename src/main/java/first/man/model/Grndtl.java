package first.man.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Grndtl
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String partcode;
    private double basecost;
    private int qty;
    private double linevalue;
    private String status;
    @ManyToOne
    @JoinColumn(name = "grn_id_fk")
    private Grn grn;


}
