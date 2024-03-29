package ch.supsi.webapp.web.Repository;
import ch.supsi.webapp.web.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByName(String name);

}
