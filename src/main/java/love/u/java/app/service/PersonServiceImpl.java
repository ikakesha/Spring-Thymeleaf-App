package love.u.java.app.service;

import love.u.java.app.model.Person;
import love.u.java.app.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Irakli on 12/8/2015.
 */

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepo personRepo;

    @Override
    public List getPerson() {
        return personRepo.getPerson();
    }

    @Override
    public List getPersonById(Long id) {
        return personRepo.getPersonById(id);
    }

    @Override
    public List getPersonByName(String name){return personRepo.getPersonByName(name);}

    @Override
    public void removePersonById(Long id){personRepo.removePersonById(id);}

    @Override
    public void update(Long id, String newName, Long newAge){personRepo.update(id,newName, newAge);}

    @Override
    public boolean savePerson(Person person) {
        return personRepo.savePerson(person);
    }
}
