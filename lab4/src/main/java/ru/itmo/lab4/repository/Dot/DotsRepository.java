package ru.itmo.lab4.repository.Dot;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.lab4.model.Dot;

@org.springframework.stereotype.Repository
public interface DotsRepository extends JpaRepository<Dot,Long> {
}
