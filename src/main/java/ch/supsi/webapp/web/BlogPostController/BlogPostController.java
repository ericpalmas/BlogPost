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
            for(int i=0;i<blogPosts.size();++i){
                if(blogPosts.get(i).getId()==id){
                    blogPosts.set(i,blogPost);
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
                    blogPosts.remove(b);
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
            for (int i=0;i<blogPosts.size();i++) {
                if (blogPosts.get(i).getId() == id) {
                    if (blogPost.getTitle() != null)
                        blogPosts.get(i).setTitle(blogPost.getTitle());
                    if (blogPost.getText() != null)
                        blogPosts.get(i).setText(blogPost.getText());
                    if (blogPost.getAuthor() != null)
                        blogPosts.get(i).setAuthor(blogPost.getAuthor());
                    return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
    }
}
