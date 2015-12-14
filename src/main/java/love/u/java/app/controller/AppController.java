package love.u.java.app.controller;

import love.u.java.app.model.Person;
import love.u.java.app.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Irakli on 12/8/2015.
 */

@Controller
@RequestMapping(value = "/")
public class AppController {

    private static Logger log = Logger.getLogger(AppController.class);

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homePage() {

        return new ModelAndView("home-page");

    }

    @RequestMapping(value = "/getperson")
    @ResponseBody
    public List<Person> getPerson() {
        List<Person> p = personService.getPerson();

        return p;
    }

    @RequestMapping(value = "/getperson/{id}")
    @ResponseBody
    public List<Person> getPersonById(@PathVariable Long id) {

        List<Person> p = personService.getPersonById(id);

        return p;
    }

    @RequestMapping(value = "/removeperson/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String removePersonById(@PathVariable Long id) {

        personService.removePersonById(id);

        return "Person has been successfully deleted from DB!";

    }

    @RequestMapping(value = "/remove-from", method = RequestMethod.GET)
    public ModelAndView removeForm() {

        ModelAndView mav = new ModelAndView("remove-from");
        mav.addObject("person", new Person());

        return mav;
    }

    @RequestMapping(value = "/remove-process", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView removeProcess(@ModelAttribute Person person) {

        personService.removePersonById(person.getId());

        return new ModelAndView("success");
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateForm() {

        ModelAndView mav = new ModelAndView("update");
        mav.addObject("person", new Person());

        return mav;
    }

    @RequestMapping(value = "/update-process", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateProcess(@ModelAttribute Person person) {

        personService.update(person.getId(), person.getName(), person.getAge());

        return new ModelAndView("success");
    }


    @RequestMapping(value = "/success-page", method = RequestMethod.GET)
    public ModelAndView success() {

        return new ModelAndView("success");

    }

    @RequestMapping(value = "/error-page", method = RequestMethod.GET)
    public ModelAndView error() {

        return new ModelAndView("error");

    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView inputForm() {

        ModelAndView mav = new ModelAndView("input-form");
        mav.addObject("person", new Person());

        return mav;
    }

    @RequestMapping(value = "/form-ajax", method = RequestMethod.GET)
    public ModelAndView inputFormAjax() {
        return new ModelAndView("input-form-ajax");
    }


    /*Java manipulation*/
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public ModelAndView inputProcess(@ModelAttribute Person person) {

        boolean insertResult = personService.savePerson(person);

        if (insertResult) {
            return new ModelAndView("redirect:/success-page");
        } else {
            return new ModelAndView("redirect:/error-page");
        }

    }


    /*Javascript manipulation with JSON*/
    @RequestMapping(value = "/process-ajax", method = RequestMethod.POST, consumes = "application/json")
    public ModelAndView inputProcessAjax(@RequestBody Person person) {

        log.info("name: " + person.getName());
        log.info("age: " + person.getAge());

        boolean insertResult = personService.savePerson(person);

        if (insertResult) {
            return new ModelAndView("redirect:/success-page");
        } else {
            return new ModelAndView("redirect:/error-page");
        }

    }


}
