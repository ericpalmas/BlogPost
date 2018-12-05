package ch.supsi.webapp.web.Model;
import javax.persistence.*;
import  java.util.Date;

@Entity
public class BlogPost {
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

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
    @JoinColumn (referencedColumnName = "name")
    private Category category;

    public BlogPost(Date date, String title, String text, User author, Category category) {
        this.date = date;
        this.title = title;
        this.text = text;
        this.author = author;
        this.category = category;
        this.date = new Date();
    }

    public BlogPost() {  this.date = new Date();}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    @Override
    public String toString() {
        return "BlogPost{" +
                "date=" + date +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", id=" + id +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}

