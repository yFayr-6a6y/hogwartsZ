package hogwarts.demo.repository;

import hogwarts.demo.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Collection<Faculty> findByColor(String color);

    Collection<Faculty> findByName(String name);

    Collection<Faculty> findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(
            String namePart,
            String colorPart
    );

    Collection<Faculty> findByNameStartingWith(String prefix);

    Collection<Faculty> findByNameEndingWith(String suffix);

    Collection<Faculty> findByNameIgnoreCaseContaining(String part);
}