package ch.supsi.webapp.web.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;

public class Commento {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private ArrayList<Commento> response;
    private User author;
    private Date date;

    public Date getDate() {
        return date;
    }

    public Commento(){ }
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Commento(String description, ArrayList<Commento> response, User author) {
        this.description = description;
        this.response = response;
        this.author = author;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Commento> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Commento> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Commento{" +
                "description='" + description + '\'' +
                ", author=" + author +
                ", date=" + date +
                '}';
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
