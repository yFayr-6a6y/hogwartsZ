package hogwarts.demo.repository;

import hogwarts.demo.model.Avatar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    // Самый важный метод по критериям
    Page<Avatar> findAll(Pageable pageable);
}