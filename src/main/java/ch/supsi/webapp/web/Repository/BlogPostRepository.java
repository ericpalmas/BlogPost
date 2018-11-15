package ch.supsi.webapp.web.Repository;

import ch.supsi.webapp.web.Model.BlogPost;
import ch.supsi.webapp.web.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
     BlogPost findBlogPostById(int id);
}

