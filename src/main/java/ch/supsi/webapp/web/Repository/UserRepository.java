package ch.supsi.webapp.web.Repository;

import ch.supsi.webapp.web.Model.Role;
import ch.supsi.webapp.web.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String name);
    User findUserById(int id);
}
