package ch.supsi.webapp.web.Service;

import ch.supsi.webapp.web.Model.BlogPost;
import ch.supsi.webapp.web.Model.Category;
import ch.supsi.webapp.web.Model.Role;
import ch.supsi.webapp.web.Model.User;
import ch.supsi.webapp.web.Repository.BlogPostRepository;
import ch.supsi.webapp.web.Repository.CategoryRepository;
import ch.supsi.webapp.web.Repository.RoleRepository;
import ch.supsi.webapp.web.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class BlogPostService {

    @Autowired
    private  BlogPostRepository blogPostRepository;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private  RoleRepository roleRepository;


    @PostConstruct
    public void postContructor() {
        if (roleRepository.findAll().size() == 0) {
            roleRepository.save(new Role("admin"));
            roleRepository.save(new Role("user"));
        }

        if (userRepository.findAll().size() == 0) {
            User admin = new User(1, roleRepository.findRoleByName("admin"), "admin", "Luca", "Rossi");
            User user = new User(2, roleRepository.findRoleByName("user"), "user", "Pietro", "Verdi");
            userRepository.save(admin);
            userRepository.save(user);
        }

        if (categoryRepository.findAll().size() == 0) {
            categoryRepository.save(new Category("Sport"));
            categoryRepository.save(new Category("Cultura"));
            categoryRepository.save(new Category("Scienza"));

        }

        if (blogPostRepository.findAll().size() == 0) {
            BlogPost blogPost = new BlogPost();
            blogPost.setAuthor(userRepository.findUserByUsername("admin"));
            blogPost.setCategory(categoryRepository.findByName("Sport"));
            blogPost.setTitle("prova");
            blogPost.setText("prova");
            create(blogPost);
        }
    }

    public List<BlogPost> getBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getBlogPostById(int id) { return blogPostRepository.findBlogPostById(id);}

    public Integer getIdByName(String name){return userRepository.findIdByName(name);}

    public void create(BlogPost blogPost) { blogPostRepository.save(blogPost); }

    public boolean delete(int id) {
        for (BlogPost blogpost : blogPostRepository.findAll()) {
            if (blogpost.getId() == id) {
                blogPostRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public boolean modify(int id, BlogPost newBlogPost) {
        BlogPost blogpost = blogPostRepository.findBlogPostById(id);
        if(blogpost!=null){
            blogpost.setTitle(newBlogPost.getTitle());
            blogpost.setAuthor(newBlogPost.getAuthor());
            blogpost.setCategory(newBlogPost.getCategory());
            blogpost.setText(newBlogPost.getText());
            blogPostRepository.save(blogpost);
            return true;
        }
        return false;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}


