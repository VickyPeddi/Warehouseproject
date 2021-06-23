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
public class User {
    @Id
//	@GeneratedValue(generator = "usertype_seq")
//	@SequenceGenerator(name = "usertype_seq", sequenceName = "usertype_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;
    private String usertype;
    private String usercode;
    private String userfor;
    private String useremail;
    private String usercontact;
    private String useridtype;
    private String ifother;
    private String idnumber;
    @ManyToOne
    @JoinColumn(name="order_user")
    private Ordermethod order;//hasrelationship


}
