package prodcat.miniprojet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prodcat.miniprojet.data.moduls.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(long idCAt);
    @Query(value = "SELECT * from product Where category_id=:idb ", nativeQuery = true)
    public List<Product> showProductsInCat(@Param("idb") long idb);
}
