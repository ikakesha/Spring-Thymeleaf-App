package love.u.java.app.repo;

import love.u.java.app.model.Person;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Irakli on 12/8/2015.
 */

@Repository
public class PersonRepoImpl implements PersonRepo {

    private static Logger log = Logger.getLogger(PersonRepoImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List getPerson() {

        List list = sessionFactory.getCurrentSession().createQuery("from love.u.java.app.model.Person p").list();

        return list;
    }


    @Override
    public List getPersonById(Long id) {

        List list = sessionFactory.getCurrentSession().createQuery("from love.u.java.app.model.Person p where p.id =:id").setParameter("id", id).list();

        return list;

    }

    @Override
    public List getPersonByName(String name){

        List list = sessionFactory.getCurrentSession().createQuery("from from love.u.java.app.model.Person p where p.name =:name").setParameter("name", name).list();

        return list;
    }

    @Override
    public void removePersonById(Long id){

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Person p = (Person) session.load(Person.class, id);
            session.delete(p);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
        }



    }
    @Override
    public void update(Long id, String newName, Long newAge){
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Person p = (Person) session.load(Person.class, id);
            p.setName(newName);
            p.setAge(newAge);
            session.update(p);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
        }
    }

    public boolean savePerson(Person person) {

        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(person);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            log.error("Error during transaction. Reason: " + ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }


}
