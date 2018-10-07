package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/blogpost")

public class HelloServlet extends HttpServlet {
	ArrayList<BlogPost> blogPosts = new ArrayList<>();
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.getWriter().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPosts));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BlogPost blogPost = mapper.readValue(req.getInputStream(), BlogPost.class);
		blogPosts.add(blogPost);
		res.getWriter().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPost));
	}
}
