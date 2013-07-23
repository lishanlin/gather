package zip;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class ZipObjectDemo {
    public static void main(String[] args) {
        User admin = new User();
        admin.setId(new Long(1));
        admin.setUsername("admin");
        admin.setPassword("secret");
        admin.setFirstName("System");
        admin.setLastName("Administrator");

        User foo = new User();
        foo.setId(new Long(2));
        foo.setUsername("foo");
        foo.setPassword("secret");
        foo.setFirstName("Foo");
        foo.setLastName("Bar");

        System.out.println("Zipping....");
        System.out.println(admin);
        System.out.println(foo);
        try {
            FileOutputStream fos = new FileOutputStream(new File("user.dat"));
            GZIPOutputStream gos = new GZIPOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(gos);

            oos.writeObject(admin);
            oos.writeObject(foo);

            oos.flush();
            oos.close();

            gos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * {@inheritDoc)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append("; username=").append(username);
        sb.append("; password=").append(password);
        sb.append("; firstName=").append(firstName);
        sb.append("; lastName=").append(lastName);

        return sb.toString();
    }
}
