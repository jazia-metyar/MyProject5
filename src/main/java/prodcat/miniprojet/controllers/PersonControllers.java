package prodcat.miniprojet.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import prodcat.miniprojet.data.moduls.Person;
import prodcat.miniprojet.services.PersonService;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/person")
@AllArgsConstructor
public class PersonControllers {


    private PersonService personService;

    @GetMapping("/getAll")
                public List<Person> getAll(){
                    return personService.getAll();
                }

    @GetMapping("/getById/{id}")
                public Person getById(@PathVariable("id")long id) {
                    return personService.getById(id);
                }

    @PostMapping("/post")
                public Person createPerson(@RequestBody Person person) {
                    return personService.creatEntity(person);
                }


    @PutMapping("/put/{id}")
                public Person modifyPreson(@PathVariable("id")long id,
                                           @RequestBody Person newPerson) {
                    return personService.modifyEntity(id, newPerson);
                }

    @DeleteMapping("/delete/{id}")
                public Person deletePerson(@PathVariable("id")long id) {
                    return personService.deleteEntity(id);
                }




    @ExceptionHandler(NoSuchElementException.class)
                public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
                    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
                }


    @ExceptionHandler(MethodArgumentNotValidException.class)
                public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
                    StringBuilder errors = new StringBuilder();
                    for (FieldError error : e.getBindingResult().getFieldErrors()) {
                        errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
                    }
                    return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
                }



}
