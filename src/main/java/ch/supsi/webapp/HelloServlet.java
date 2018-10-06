package ch.supsi.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/blogpost")
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {
	ArrayList<BlogPost> blogPosts = new ArrayList<>();
	ObjectMapper mapper = new ObjectMapper();

	String getValue(String title, String text, String author) throws JsonProcessingException {
		blogPosts.add(new BlogPost(title,text, author));
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPosts);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.getWriter().println("get request effettuata \n\n"+ getValue(req.getParameter("title"), req.getParameter("text"),req.getParameter("author")));
		mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPosts);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.getWriter().println("post request effettuata\n\n"+ getValue(req.getParameter("title"), req.getParameter("text"),req.getParameter("author")));
		BlogPost blogPost = new BlogPost(req.getParameter("title"),req.getParameter("text"),req.getParameter("author"));
		blogPosts.add(blogPost);
		mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPost);
	}
}
