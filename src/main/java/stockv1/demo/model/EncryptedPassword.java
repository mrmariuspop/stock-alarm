package stockv1.demo.model;

import javax.persistence.*;

/**
 * @author Marius Pop
 */
@Entity
@Table(name = "Encryption")
public class EncryptedPassword {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "secret")
    private String secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EncryptedPass{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
