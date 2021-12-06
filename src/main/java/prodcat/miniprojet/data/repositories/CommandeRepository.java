package prodcat.miniprojet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prodcat.miniprojet.data.moduls.Commande;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    List<Commande> findByPerson(long idPerson);
    List<Commande> findByProduct(long idProduct);

}
