package com.jeff.gois.core.controller;


import com.jeff.gois.core.bean.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/login")
    public String page(){
        return "page/index";
    }

    @RequestMapping("/")
    public String index(Model model){
        Person person = new Person("Jeff",10);
        List<Person> people = new ArrayList<>();
        Person person1 = new Person("Andy",11);
        Person person2 = new Person("Tom",12);
        Person person3 = new Person("Jen",13);
        people.add(person1);
        people.add(person2);
        people.add(person3);
        model.addAttribute("singlePerson",person);
        model.addAttribute("people",people);
        return "login";
    }

}
