package pl.time4it.demo_store_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pl.time4it.demo_store_2.entities.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> ,
        JpaSpecificationExecutor {

    String FIND_BY_TITLE = "select * from category where name = ?1";

    @Query(value = FIND_BY_TITLE, nativeQuery = true)
    Optional<Category> findByTitle(String title);
}

