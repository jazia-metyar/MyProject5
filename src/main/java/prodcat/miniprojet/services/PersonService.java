package prodcat.miniprojet.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prodcat.miniprojet.data.moduls.Person;
import prodcat.miniprojet.data.repositories.PersonRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {
   @Autowired
    private PersonRepository personRepository;

    public List<Person> getAll() {

        return personRepository.findAll();
    }


    public Person getById(long id) {
        Optional<Person> opt = personRepository.findById(id);
        Person entity;
        if(opt.isPresent())
            entity = opt.get();
        else
            throw new NoSuchElementException("Person with this id is not found");

        return entity;
    }


    public Person creatEntity(Person p)
    {
   return  personRepository.save(p);
    }



    public Person modifyEntity(long id, Person newEntity) {
        Person entity = this.getById(id);

        if(newEntity.getFirstName() != null)
            entity.setFirstName(newEntity.getFirstName());
       /* if(newEntity.getDateOfBirth() != null)
            entity.setDateOfBirth(newEntity.getDateOfBirth());*/
      /*  if(newEntity.getRole() != null)
            entity.setRole(newEntity.getRole());*/

        if(newEntity.getLastName() != null)
            entity.setLastName(newEntity.getLastName());

        if(newEntity.getPhoneNb() != null)
            entity.setPhoneNb(newEntity.getPhoneNb());

        if(newEntity.getEmail() != null)
            entity.setEmail(newEntity.getEmail());

        if(newEntity.getPassword() != null)
            entity.setPassword(newEntity.getPassword());



        return personRepository.save(entity);
    }




    public Person deleteEntity(long id) {
        Person  entity = this.getById(id);
        personRepository.deleteById(id);
        return entity;
    }

   /* public Message senMsg(Message msg)
    {
        Message message=messageService.creatEntity(msg);
        return message;
    }*/




}
