package ch.supsi.webapp.web.BlogPostControllerThymeleaf;
import ch.supsi.webapp.web.Model.BlogPost;
import ch.supsi.webapp.web.Model.Commento;
import ch.supsi.webapp.web.Model.User;
import ch.supsi.webapp.web.Service.BlogPostService;
import ch.supsi.webapp.web.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
public class BlogPostControllerThymeleaf {
    @Autowired
    private BlogPostService blogPostService;

    private CustomUserDetailService customUserDetailService;

    private BCryptPasswordEncoder cryptPasswordEncoder;


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
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
        for(Integer deletedId : blogPostService.getDeletedPosts()){
            if(deletedId==id){
                return "errorPage";
            }
        }
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

    @PostMapping("/blog/new")
    public String submit(BlogPost blogPost) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        blogPost.setAuthor(blogPostService.getUserByUsername(user.getUsername()));
        blogPostService.create(blogPost);
        return "redirect:/";
    }


    @GetMapping("/blog/{id}/comments")
    public String writeComment(Model model, @PathVariable int id,@ModelAttribute Commento commento) {
        model.addAttribute("blogpost", blogPostService.getBlogPostById(id));
        model.addAttribute("commento",commento);
        return "writeComment";
    }

    @PostMapping("/blog/{id}/comments")
    public String addComment(@PathVariable int id,Commento commento,@ModelAttribute BlogPost blogPost) {


        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commento.setAuthor(blogPostService.getUserByUsername(user.getUsername()));


        commento.setDate(new Date());
        System.out.println(commento);
        //blogPostService.getBlogPostById(id).setComment(commento);
        System.out.println(blogPostService.getBlogPostById(id));
        blogPost.setComment(commento);
        blogPostService.modify(id,blogPostService.getBlogPostById(id));
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/edit")
    public String put(Model model, @PathVariable int id) {
        model.addAttribute("blogpost", blogPostService.getBlogPostById(id));
        model.addAttribute("categories", blogPostService.getCategories());
        model.addAttribute("users", blogPostService.getUsers());
        return "addBlogForm";
    }

    @PostMapping("/blog/{id}/edit")
    public String modify(@PathVariable int id, BlogPost blogPost) {
        blogPostService.modify(id,blogPost);
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable int id) {
        blogPostService.delete(id);
        return "redirect:/";
    }
}
