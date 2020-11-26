package ru.itmo.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.lab4.model.Dot;
import ru.itmo.lab4.model.User;
import ru.itmo.lab4.repository.Dot.DotsRepository;
import ru.itmo.lab4.repository.User.UsersRepository;
import ru.itmo.lab4.springDataJdbc.DotJdbcDao;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class DotsController {

//    @Autowired
//    private DotsRepository dotRepository;

    @Autowired
    private DotJdbcDao dotJdbcDao;


    @GetMapping("dots")
    public List<Dot> getDots()
    {
        return this.dotJdbcDao.findAll();
//        return this.dotRepository.findAll();
    }

    @PostMapping("dots")
    public ResponseEntity<?> addorUpdateExpense(@RequestBody Dot dot) {
        if(validate(dot)) {
            checkResult(dot);
            this.dotJdbcDao.insert(dot);
//        this.dotRepository.save(dot);
            return new ResponseEntity("Expense added successfully", HttpStatus.OK);
        }
        return new ResponseEntity("Expense wasn't added", HttpStatus.OK);
    }


    @DeleteMapping("dots")
    public void deletePost() {
        this.dotJdbcDao.deleteAllRecords();
//        this.dotRepository.deleteAll();
    }

    private boolean validate(Dot dot){
        try {
            double x = dot.getX();
            double y = dot.getY();
            double r = dot.getR();

            if (!(x % 1 == 0 && x <= 3 && x >= -5)) {
                return false; //error
            } else if (!(y < 5 && y > -3)){
                return false; //error
            } else if(!( r % 1 == 0 && r >= 1 && r <= 5)){
                return false; //error
            }
            return true;
        }
        catch (Exception e){
            return false; //error
        }
    }

    private void checkResult(Dot dot) {
        double x = dot.getX();
        double y = dot.getY();
        double r = dot.getR();

        if (x >= 0) {
            if (y >= 0) {
                if (y <= (r-x)) {
                    dot.setResult("Yes!");
                }
            }
            else {
                if (y >= (-r) && x <= (r/2)) {
                    dot.setResult("Yes!");
                }
            }
        } else {
            if (y <= 0) {
                if ((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r, 2)) {
                    dot.setResult("Yes!");
                }
            }
        }
        if (dot.getResult() == null) {
            dot.setResult("No!");
        }
    }

}

