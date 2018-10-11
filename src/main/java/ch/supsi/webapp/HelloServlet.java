package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/blogpost")
@SuppressWarnings("serial")

public class HelloServlet extends HttpServlet {
	private ArrayList<BlogPost> blogPosts = new ArrayList<>();
	private ObjectMapper mapper = new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setStatus(200);
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		res.getWriter().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPosts));
		writer.flush();
		writer.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws  IOException {
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		res.setStatus(200);
		if (req.getParameterMap().size()!=0){
				BlogPost requestPostBlog = new BlogPost(req.getParameter("title"), req.getParameter("text"), req.getParameter("author"));
				blogPosts.add(requestPostBlog);
				writer.println(mapper.writeValueAsString(requestPostBlog));
		} else {
			BlogPost blogPost = mapper.readValue(req.getInputStream(), BlogPost.class);
			blogPosts.add(blogPost);
			writer.println(mapper.writeValueAsString(blogPost));
		}
		writer.flush();
		writer.close();
	}
}


