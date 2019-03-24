package ch.supsi.webapp.web.Repository;

import ch.supsi.webapp.web.Model.BlogPost;
import ch.supsi.webapp.web.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
     BlogPost findBlogPostById(int id);
     List<BlogPost> findAllByTitleContainingOrTextContaining(String title, String text);
}

