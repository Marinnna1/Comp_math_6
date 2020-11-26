package ru.itmo.lab4.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jndi.support.SimpleJndiBeanFactory;
import ru.itmo.lab4.repository.User.UsersRepository;

import java.math.BigDecimal;

public class Example {

    public static void main(String[] args) {
       // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      //  UsersRepository repository = context.getBean(UsersRepository.class);
//
        User user = new User();
        user.setLogin("rbthnyjm");
        user.setPassword("brnhtj");
        user.setId(10L);
   //     repository.save(user);
    }
}