package ch.supsi.webapp.web.Model;
import javax.persistence.*;
import java.util.List;


@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Category(String name) { this.name = name; }

    public Category() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
