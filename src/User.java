import java.io.Serializable;

public class User implements Serializable {

    private String name;


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "imie: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((User) obj).getName());
    }
}
