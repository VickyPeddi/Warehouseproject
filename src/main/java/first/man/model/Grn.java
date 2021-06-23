package first.man.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
//	@OneToOne
//	@JoinColumn(name = "purchase order")
//	//no dropdown
//	private Purchaseorder purchaseorder;
	private String description;

}
