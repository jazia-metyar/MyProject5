package prodcat.miniprojet.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import prodcat.miniprojet.data.dto.ProductDto;
import prodcat.miniprojet.data.moduls.Product;
import prodcat.miniprojet.data.repositories.ProductRepository;
import prodcat.miniprojet.services.ProductService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/product")
@RestController
@AllArgsConstructor
public class ProductController {
    private ModelMapper mapper;
    private ProductRepository productRepository;
    private ProductService productService;


    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable("id") long id) {

        return productRepository.findById(id);
    }

    @GetMapping("prod/{id}")
    public List<Product> getProdByCateg(@PathVariable("id") long id) {
        return productRepository.findByCategoryId(id);
    }

    @PostMapping
    public Product createCategory(@RequestBody ProductDto productDto) {
        var product = mapper.map(productDto, Product.class);
        return productService.createEntity(product);
    }

    @PutMapping("/{id}")
    public Product modifyCategory(@PathVariable("id") long id,
                                  @RequestBody ProductDto productDto) {
        var product = mapper.map(productDto, Product.class);

        return productService.modifyEntity(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Product product) {

        productRepository.delete(product);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var stringBuilderrors = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            stringBuilderrors.append(error.getField() + ": " + error.getDefaultMessage() + ".\n");
        }
        return new ResponseEntity<>(stringBuilderrors.toString(), HttpStatus.BAD_REQUEST);
    }
}
