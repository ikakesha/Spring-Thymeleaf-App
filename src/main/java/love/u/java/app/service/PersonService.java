package love.u.java.app.service;

import love.u.java.app.model.Person;

import java.util.List;

/**
 * Created by Irakli on 12/8/2015.
 */
public interface PersonService {
    public List getPerson();

    public List getPersonById(Long id);

    public List getPersonByName(String name);

    public void removePersonById(Long id);

    public void update(Long id, String newName, Long newAge);

    public boolean savePerson(Person person);

}
