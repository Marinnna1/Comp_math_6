package ru.itmo.lab4.springDataJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itmo.lab4.model.Dot;
import ru.itmo.lab4.model.User;

import java.util.List;

@Repository
public class DotJdbcDao {
        @Autowired
        JdbcTemplate jdbcTemplate;

        public List<Dot> findAll(){
            List<Dot> dots = jdbcTemplate.query("select * from public.dots",
                    new BeanPropertyRowMapper<>(Dot.class));
            return dots;
        }

        public int insert (Dot dot){
            return jdbcTemplate.update("insert into public.dots "+
                            "(x, y, r, result) " +
                            "values (?,?,?,?)",
                    new Object[]{
                            dot.getX(), dot.getY(), dot.getR(), dot.getResult()
                    });
        }

        public void deleteAllRecords(){
            jdbcTemplate.update("DELETE from public.dots");
        }
    }


