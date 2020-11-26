package ru.itmo.lab4.springDataJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itmo.lab4.model.User;

import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findAll(){
         List<User> people = jdbcTemplate.query("select * from public.register",
                new BeanPropertyRowMapper<>(User.class));
        return people;
    }

    public int insert (User user){
        return jdbcTemplate.update("insert into public.register "+
                "(login, password) " +
                "values (?,?)",
                new Object[]{
                        user.getLogin(), user.getPassword()
                });
    }

    public void deleteAllRecords(){
        jdbcTemplate.update("DELETE from public.register");
    }
}
