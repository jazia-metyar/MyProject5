package prodcat.miniprojet.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prodcat.miniprojet.data.moduls.Category;
import prodcat.miniprojet.data.repositories.CategoryRepository;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class CategoryService  {
   private CategoryRepository categoryRepository;
    public Category createEntity(Category   category) {
        // set current date
      category.setDateCreation(new Timestamp(System.currentTimeMillis()));
        // save category
        return categoryRepository.save(category);
    }

    public Category modifyEntity(long id, Category category) {

        var oldCategory = categoryRepository.getById(id);

        // modification  nom
        if (category.getName() != null)
            oldCategory.setName(category.getName());

        // modification  qt
        if (category.getQt() != 0)
            oldCategory.setQt(category.getQt());

        // set current date
        oldCategory.setDateModify(new Timestamp(System.currentTimeMillis()));


        // save category
        return categoryRepository.save(oldCategory);
    }
}
