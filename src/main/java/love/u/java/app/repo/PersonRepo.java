package love.u.java.app.repo;

import love.u.java.app.model.Person;

import java.util.List;

/**
 * Created by Irakli on 12/8/2015.
 */
public interface PersonRepo {

        public List getPerson();

        public List getPersonById(Long id);

        public List getPersonByName(String Name);

        public void removePersonById(Long id);

        public boolean savePerson(Person person);

        public void update(Long id, String newName, Long newAge);

}
