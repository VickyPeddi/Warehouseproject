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
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer docid;
    private String docname;
    @Lob
    private byte[] docdata;
}
