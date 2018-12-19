package ch.supsi.webapp.web.BlogPostControllerThymeleaf;
import ch.supsi.webapp.web.Model.BlogPost;
import ch.supsi.webapp.web.Model.Role;
import ch.supsi.webapp.web.Model.User;
import ch.supsi.webapp.web.Service.BlogPostService;
import ch.supsi.webapp.web.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BlogPostControllerThymeleaf {
    @Autowired
    private BlogPostService blogPostService;

    private CustomUserDetailService customUserDetailService;

    private BCryptPasswordEncoder cryptPasswordEncoder;


    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(User user) {
        if (blogPostService.isPresent(user))
            return "redirect:/";
        blogPostService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(Model model, @ModelAttribute User user) {
        model.addAttribute("user",user);
        return "login";
    }


    @GetMapping("/")
    public String get(Model model,@ModelAttribute User user) {
        if(blogPostService.getBlogPosts().size()<3) {
            model.addAttribute("posts", blogPostService.getBlogPosts().subList(0, blogPostService.getBlogPosts().size()));
        }
        else {
            model.addAttribute("posts", blogPostService.getBlogPosts().subList(0, 3));
        }
        model.addAttribute("blogposts", blogPostService.getBlogPosts());
        return "home";
    }

    @GetMapping("/blog/{id}")
    public String blogpostDetails(Model model, @PathVariable int id) {
        if (blogPostService.getBlogPostById(id) != null)
            model.addAttribute("post", blogPostService.getBlogPostById(id));
        return "blogDetails";
    }

    @GetMapping("/blog/new")
    public String createBlogForm(Model model, @ModelAttribute BlogPost blogPost) {
        model.addAttribute("blogpost", blogPost);
        model.addAttribute("categories", blogPostService.getCategories());
        model.addAttribute("users", blogPostService.getUsers());
        return "addBlogForm";
    }

//    @PostMapping("/blog/new")
//    public String submit(BlogPost blogPost, @RequestParam("author_name") String name) {
//        //blogPost.setAuthor(blogPostService.get);
//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        blogPost.setAuthor(blogPostService.getUserByUsername(user.getUsername()));
//        blogPostService.create(blogPost);
//        return "redirect:/";
//    }

    @PostMapping("/blog/new")
    public String submit(BlogPost blogPost) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blogPost.setAuthor(blogPostService.getUserByUsername(user.getUsername()));
        blogPostService.create(blogPost);
        return "redirect:/";
    }


    @GetMapping("/blog/{id}/edit")
    public String put(Model model, @PathVariable int id, @ModelAttribute BlogPost blogPost) {
        model.addAttribute("blogpost", blogPostService.getBlogPostById(id));
        model.addAttribute("categories", blogPostService.getCategories());
        model.addAttribute("users", blogPostService.getUsers());
        return "addBlogForm";
    }

    @PostMapping("/blog/{id}/edit")
    public String modify(@PathVariable int id, @ModelAttribute BlogPost blogPost) {
        blogPostService.modify(id,blogPost);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable int id) {
        blogPostService.delete(id);
        return "redirect:/";
    }
}
