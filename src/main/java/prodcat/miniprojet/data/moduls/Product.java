package prodcat.miniprojet.data.moduls;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private  int qt;
    private  boolean dispo;
    private Timestamp dateCreation;
    private Timestamp dateModify;
   @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Commande> commandeList = new ArrayList<>();

}
