package prodcat.miniprojet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import prodcat.miniprojet.data.moduls.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
