package hogwarts.demo.repository;

import hogwarts.demo.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // точное совпадение цвета
    Collection<Faculty> findByColor(String color);

    // точное совпадение названия
    Collection<Faculty> findByName(String name);

    // название или цвет содержит подстроку (без учёта регистра)
    Collection<Faculty> findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(
            String namePart,
            String colorPart
    );

    // название начинается с указанной строки
    Collection<Faculty> findByNameStartingWith(String prefix);

    // название заканчивается на указанную строку
    Collection<Faculty> findByNameEndingWith(String suffix);

    // факультеты с названием, содержащим подстроку (без учёта регистра)
    Collection<Faculty> findByNameIgnoreCaseContaining(String part);
}