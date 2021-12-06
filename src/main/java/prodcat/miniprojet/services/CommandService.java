package prodcat.miniprojet.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prodcat.miniprojet.data.moduls.Commande;
import prodcat.miniprojet.data.moduls.Person;
import prodcat.miniprojet.data.moduls.Product;
import prodcat.miniprojet.data.repositories.CommandeRepository;
import prodcat.miniprojet.data.repositories.PersonRepository;
import prodcat.miniprojet.data.repositories.ProductRepository;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommandService {
    private ProductRepository productRepository;
    private PersonRepository personRepository;
    private CommandeRepository commandeRepository;

    public Commande createEntity(Commande commande) {
        // set current date
        commande.setDateCreation(new Timestamp(System.currentTimeMillis()));
        // save Product
        Optional<Product> optionalProduct = productRepository.findById(commande.getProduct().getId());
        Product product;
        if (optionalProduct.isPresent())
            product = optionalProduct.get();
        else
            throw new NoSuchElementException("product with this id is not found");
        commande.setProduct(product);

        // save Person
        Optional<Person> optionalPerson = personRepository.findById(commande.getPerson().getId());
        Person person;
        if (optionalPerson.isPresent())
            person = optionalPerson.get();
        else
            throw new NoSuchElementException("person with this id is not found");
        commande.setPerson(person);


        return commandeRepository.save(commande);

    }


}
