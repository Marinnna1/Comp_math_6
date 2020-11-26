package ru.itmo.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.lab4.repository.Dot.DotsRepository;

@SpringBootApplication
public class Lab4Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }


    @Autowired
    private DotsRepository dotRepository;
    @Override
    public void run(String... args) throws Exception {

      //  this.dotRepository.save(new Dot("1","2","3"));
      //  this.dotRepository.save(new Dot("10","20","30"));
    }
}
