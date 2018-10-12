package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/blogpost/*")
@SuppressWarnings("serial")

public class HelloServlet extends HttpServlet {
	private ArrayList<BlogPost> blogPosts = new ArrayList<>();
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setStatus(200);
		res.setContentType("application/json");
		PrintWriter writer = res.getWriter();
		if(req.getPathInfo()==null){
			res.getWriter().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPosts));
		}else{
			Integer index = Integer.parseInt(req.getPathInfo().substring(1));
			try {
                res.getWriter().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(blogPosts.get(index)));
            }catch (IndexOutOfBoundsException e){
                writer.println("nessun blog trovato");
                return;
            }
		}
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
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        if (req.getPathInfo() != null) {
            Integer index = Integer.parseInt(req.getPathInfo().substring(1));
            try {
                if (!req.getParameterMap().isEmpty()) {
                    BlogPost blogPost = new BlogPost(req.getParameter("title"), req.getParameter("text"), req.getParameter("author"));
                    blogPosts.set(index, blogPost);
                    writer.println(mapper.writeValueAsString(blogPost));
                    res.setStatus(200);
                }
            } catch (IndexOutOfBoundsException e) {
                writer.println("nessun blogpost trovato");
                res.setStatus(404);
                return;
            }
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        if (req.getPathInfo() != null) {
            Integer index = Integer.parseInt(req.getPathInfo().substring(1));
            try {
                writer.println(mapper.writeValueAsString(blogPosts.get(index)));
                blogPosts.remove((int)index);
                res.setStatus(200);
            } catch (IndexOutOfBoundsException e) {
                writer.println("nessun blogpost trovato");
                res.setStatus(404);
                return;
            }
        }
        writer.flush();
        writer.close();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        if(req.getMethod().equals("PATCH")){
            if (req.getPathInfo() != null) {
                Integer index = Integer.parseInt(req.getPathInfo().substring(1));
                try {
                    if (!req.getParameterMap().isEmpty()) {
                        if (req.getParameter("title") != null)
                            blogPosts.get(index).setTitle(req.getParameter("title"));
                        if (req.getParameter("text") != null)
                            blogPosts.get(index).setText(req.getParameter("text"));
                        if (req.getParameter("author") != null)
                            blogPosts.get(index).setAuthor(req.getParameter("author"));
                        writer.println(mapper.writeValueAsString(blogPosts.get(index)));
                        res.setStatus(200);
                    }
                } catch (IndexOutOfBoundsException e) {
                    writer.println("nessun blogpost trovato");
                    res.setStatus(404);
                    return;
                }
            }
            writer.flush();
            writer.close();
        }else{
            super.service(req,res);
        }
    }
}
