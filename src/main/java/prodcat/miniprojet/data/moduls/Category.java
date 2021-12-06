package prodcat.miniprojet.data.moduls;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int qt;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Product> product = new ArrayList<>();
    private Timestamp dateCreation;
    private Timestamp dateModify;

}
