package ch.supsi.webapp.web.BlogPostControllerThymeleaf;
import ch.supsi.webapp.web.Model.BlogPost;
import ch.supsi.webapp.web.Service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BlogPostControllerThymeleaf {
    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/")
    public String get(Model model) {
        if(blogPostService.getBlogPosts().size()<3)
            model.addAttribute("posts", blogPostService.getBlogPosts().subList(0,blogPostService.getBlogPosts().size()));
        else
            model.addAttribute("posts", blogPostService.getBlogPosts().subList(0,3));
        model.addAttribute("blogposts", blogPostService.getBlogPosts());
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blogpostDetails(Model model, @PathVariable int id) {
        if (blogPostService.getBlogPostById(id) != null)
            model.addAttribute("post", blogPostService.getBlogPostById(id));
        return "blogpostDetails";
    }

    @GetMapping("/blog/new")
    public String createBlogForm(Model model, @ModelAttribute BlogPost blogPost) {
        model.addAttribute("blogpost", blogPost);
        model.addAttribute("categories", blogPostService.getCategories());
        model.addAttribute("users", blogPostService.getUsers());
        return "createBlogForm";
    }

    @PostMapping("/blog/new")
    public String submit(BlogPost blogPost) {
        blogPostService.create(blogPost);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/edit")
    public String put(Model model, @PathVariable int id, @ModelAttribute BlogPost blogPost) {
        model.addAttribute("blogpost", blogPostService.getBlogPostById(id));
        model.addAttribute("categories", blogPostService.getCategories());
        model.addAttribute("users", blogPostService.getUsers());
        return "createBlogForm";
    }

    @PostMapping("/blog/{id}/edit")
    public String modify(@PathVariable int id, @ModelAttribute BlogPost blogPost) {
        blogPostService.modify(id,blogPost);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/delete")
    public String delte(@PathVariable int id) {
        blogPostService.delete(id);
        return "redirect:/";
    }
}
