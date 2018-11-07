package ch.supsi.webapp.web.BlogPostController;

import ch.supsi.webapp.web.BlogPost.BlogPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogPostController {
    private List<BlogPost> blogPosts = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value="/blogposts", method = RequestMethod.GET)
    public List<BlogPost> get() {
        return blogPosts;
    }

    @RequestMapping(value="/blogposts", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> post(@RequestBody BlogPost blogpost){
        blogPosts.add(blogpost);
        return new ResponseEntity<BlogPost>(blogpost, HttpStatus.CREATED);
    }

    @RequestMapping(value="/blogposts/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getBlogPost(@PathVariable int id) {
        try{
            for(BlogPost blogPost : blogPosts){
                if(blogPost.getId()==id) {
                    return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
                }
            }
        }catch (IndexOutOfBoundsException  ex){
            return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/blogposts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BlogPost> put(@PathVariable int id,@RequestBody BlogPost blogPost){
        try{
            for(BlogPost b : blogPosts){
                if(b.getId()==id){
                    blogPosts.set(id,blogPost);
                    return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
                }
            }
        }catch (IndexOutOfBoundsException ex){
            return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/blogposts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> delete(@PathVariable int id){
        try{
            for(BlogPost b : blogPosts){
                if(b.getId()==id){
                    JSONObject object = new JSONObject();
                    object.put("success", true);
                    blogPosts.remove(id);
                    return new ResponseEntity<JSONObject>(object, HttpStatus.OK);
                }
            }
        }catch (IndexOutOfBoundsException ex){
            return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<BlogPost> patch(@PathVariable int id, @RequestBody BlogPost blogPost) {
        try {
            for (BlogPost b : blogPosts) {
                if (b.getId() == id) {
                    if (blogPost.getTitle() != null)
                        blogPosts.get(id).setTitle(blogPost.getTitle());
                    if (blogPost.getText() != null)
                        blogPosts.get(id).setText(blogPost.getText());
                    if (blogPost.getAuthor() != null)
                        blogPosts.get(id).setAuthor(blogPost.getAuthor());
                    return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
    }
}
