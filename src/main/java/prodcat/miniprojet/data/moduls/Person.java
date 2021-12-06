package prodcat.miniprojet.data.moduls;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name ="Person" )
public  class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     long id;

    @Column(length = 50, nullable = false)
     String firstName;

    @Column(length = 50, nullable = false)
     String lastName;

    @Column(length = 20, nullable = false)
     String phoneNb;

    @Column(length = 20, nullable = false)
    String password ;

    @Column(length = 30, nullable = false)
     String email ;


  @OneToMany(mappedBy = "person", fetch = FetchType.EAGER,
          cascade = CascadeType.REMOVE)
  @JsonIgnore
  private List<Commande> commandes= new ArrayList<>();



}
