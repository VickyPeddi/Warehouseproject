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
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int partid;
    private String partcode;
    private double partwidth;
    private double partlen;
    private double partheight;
    private double basecost;
    private String basecurrency;
    private String description;
    @ManyToOne
    //many uoms belongs to one part
    @JoinColumn(name = "uom_part")
    //has-A relation
    private Uommodel uommodel;

}
