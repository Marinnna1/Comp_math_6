package ru.itmo.lab4.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.lab4.model.User;

@org.springframework.stereotype.Repository
public interface UsersRepository extends JpaRepository<User,Long> {
}

