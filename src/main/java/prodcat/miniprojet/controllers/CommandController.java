package prodcat.miniprojet.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import prodcat.miniprojet.data.moduls.Commande;
import prodcat.miniprojet.data.repositories.CommandeRepository;
import prodcat.miniprojet.services.CommandService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/command")
@RestController
@AllArgsConstructor
public class CommandController {

    private CommandeRepository commandeRepository;
    private CommandService commandService;


    @GetMapping
    public List<Commande> getAll() {
        return commandeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Commande> getById(@PathVariable("id") long id) {

        return commandeRepository.findById(id);
    }

    @GetMapping("Product/{id}")
    public List<Commande> getComdByProd(@PathVariable("id") long id) {
        return commandeRepository.findByProduct(id);
    }

    @GetMapping("person/{id}")

    public List<Commande> getComdByPers(@PathVariable("id") long id) {
        return commandeRepository.findByPerson(id);
    }

    @PostMapping
    public Commande createCategory(@RequestBody Commande commande) {

        return commandService.createEntity(commande);
    }


    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Commande commande) {

        commandeRepository.delete(commande);
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
