package ch.supsi.webapp.web.Model;
import javax.persistence.*;


@Entity
public class BlogPost {
    private String title;

    @Column(columnDefinition = "TEXT")
    private String text;
    @Id
    @GeneratedValue
    private int id;



    @ManyToOne
    @JoinColumn (referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn (referencedColumnName = "id")
    private Category category;

    public BlogPost(int id, String title, String text, User author, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
        this.category = category;
    }

    public BlogPost() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

