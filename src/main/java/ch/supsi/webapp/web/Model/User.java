package ch.supsi.webapp.web.Model;
import javax.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Role role;

    private String username;

    private String name;
    private String surname;

    public User() { }

    public User(int id, Role role, String username, String name, String surname) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
