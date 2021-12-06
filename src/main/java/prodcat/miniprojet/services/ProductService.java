package prodcat.miniprojet.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prodcat.miniprojet.data.moduls.Category;
import prodcat.miniprojet.data.moduls.Product;
import prodcat.miniprojet.data.repositories.CategoryRepository;
import prodcat.miniprojet.data.repositories.ProductRepository;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public Product createEntity(Product product) {
        // set current date
        product.setDateCreation(new Timestamp(System.currentTimeMillis()));
        // save category
        Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());
        Category category;
        if (optionalCategory.isPresent())
            category = optionalCategory.get();
        else
            throw new NoSuchElementException("Category with this id is not found");
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product modifyEntity(long id, Product product) {

        var oldProduct = productRepository.getById(id);

        // modification  nom
        if (product.getName() != null)
            oldProduct.setName(product.getName());

        // modification  qt
        if (product.getQt() != 0)
            oldProduct.setQt(product.getQt());

        // set current date
        oldProduct.setDateModify(new Timestamp(System.currentTimeMillis()));


        // save category
        if(product.getCategory()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(product.getCategory().getId());
            Category category;
            if (optionalCategory.isPresent())
                category = optionalCategory.get();
            else
                throw new NoSuchElementException("Category with this id is not found");
            oldProduct.setCategory(category);
        }
        return productRepository.save(oldProduct);
    }

}
