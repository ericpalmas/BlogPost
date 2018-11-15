package ch.supsi.webapp.web.BlogPostController;
import ch.supsi.webapp.web.Model.BlogPost;
import ch.supsi.webapp.web.Repository.BlogPostRepository;
import ch.supsi.webapp.web.Repository.CategoryRepository;
import ch.supsi.webapp.web.Repository.RoleRepository;
import ch.supsi.webapp.web.Repository.UserRepository;
import ch.supsi.webapp.web.Service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

@RestController
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;


    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value="/blogposts", method = RequestMethod.GET)
    public List<BlogPost> get() {
        return blogPostService.getBlogPostsList();
    }

    @RequestMapping(value="/blogposts", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> post(@RequestBody BlogPost blogpost){
        blogPostService.add(blogpost);
        blogpost.setAuthor(userRepository.findUserById(blogpost.getAuthor().getId()));
        blogpost.setCategory(categoryRepository.findCategoryById(blogpost.getCategory().getId()));
        return new ResponseEntity<>(blogpost, HttpStatus.CREATED);
    }

    @RequestMapping(value="/blogposts/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getBlogPost(@PathVariable int id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if(blogPost!=null){
            return new ResponseEntity<>(blogPost,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/blogposts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BlogPost> put(@PathVariable int id,@RequestBody BlogPost blogPost){
        if( blogPostService.getBlogPostById(id)!=null){
            blogPostService.edit(id, blogPost);
            return new ResponseEntity<>(blogPost, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/blogposts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> delete(@PathVariable int id){
        if( blogPostService.getBlogPostById(id)!=null){
            blogPostService.delete(id);
            JSONObject response = new JSONObject();
            response.put("success", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
