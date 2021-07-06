package first.man.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@ToString
//Good Recive Note
public class Grn {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int grnid;
	private String grncode;
	private String grntype;
	//purchase order the order code
	//no dropdown

	@ManyToOne
	@JoinColumn(unique = true,name = "po_id_fk")
	//when unique == true it is like one to one mapping else many to one
	private Purchaseorder purchaseorder;
	private String description;
	@OneToMany(mappedBy = "grn",cascade = CascadeType.ALL)
	private List<Grndtl> grndtls;

}
