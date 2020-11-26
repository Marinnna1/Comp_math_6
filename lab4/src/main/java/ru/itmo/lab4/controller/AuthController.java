package ru.itmo.lab4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.lab4.encrypt.EncryptionUtil;
import ru.itmo.lab4.model.User;
import ru.itmo.lab4.repository.Dot.DotsRepository;
import ru.itmo.lab4.repository.User.UsersRepository;
import ru.itmo.lab4.springDataJdbc.PersonJdbcDao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("auth/")
public class AuthController {  //1 - Login 0 - Registration

//    @Autowired
//    private UsersRepository usersRepository;
    private String userAnswer;

    @Autowired
    private PersonJdbcDao personJdbcDao;

    @PostMapping("users")
    public ResponseEntity<?> add(@RequestBody User user) throws NoSuchAlgorithmException {
        System.out.println(user.getLogin()+"  "+ EncryptionUtil.encrypt(user.getPassword())+"  "+user.getLogOrReg());
        if(user.getLogOrReg() == 0) { //Registration
            this.userAnswer=doRegistration(user);
        }
        else{ //Login
            this.userAnswer=doLogin(user);
        }
        return new ResponseEntity("Expense added succcessfully", HttpStatus.OK);
    }

    @GetMapping("users")
    public String getAnswer()
    {
        return userAnswer;
    }

    private String doRegistration(User user) throws NoSuchAlgorithmException {
//        List<User> userList = this.usersRepository.findAll();
        List<User> userList = this.personJdbcDao.findAll();
        if(!userList.isEmpty())
        {
            for (User us : userList)
            {
                if(us.getLogin().equals(user.getLogin())){
                   return "Пользователь с таким именем уже существует";
                }
            }
        }
//        this.usersRepository.save(user);
        user.setPassword(EncryptionUtil.encrypt(user.getPassword()));
        this.personJdbcDao.insert(user);
        return "Добро пожаловать";
    }

    private String doLogin(User user) throws NoSuchAlgorithmException {
//        List<User> userList = this.usersRepository.findAll();
        List<User> userList = this.personJdbcDao.findAll();
        if(!userList.isEmpty()) {
            for (User us : userList) {
                if(user.getLogin().equals("undefined")){return "";}
                if (us.getLogin().equals(user.getLogin())) { //LoginCheck
                    if (us.getPassword().equals(EncryptionUtil.encrypt(user.getPassword()))) { //PasswordCheck
                        return "Добро пожаловать";
                    } else {//WrongPassword
                        return "Неверный пароль";
                    }
                }
            }
        }
//        if(user.getLogin().equals("undefined")){return "";}
        return "Пользователь с таким именем не найден";

    }
}
