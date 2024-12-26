package model;

import java.sql.Timestamp;
import java.sql.Date;

public class User {

    private int id = -1;
    private String name;
    private String email;
    private String hashedPassword;
    private String salt;
    private final Date createDate;
    private Timestamp recentAccess;

    public User(String name, String email, String hashedPassword, String salt) {
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.createDate = new Date(System.currentTimeMillis());
        this.recentAccess = new Timestamp(System.currentTimeMillis());
    }

    public User(int id, String name, String email, String hashedPassword, String salt, Date createDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.createDate = createDate;
        this.recentAccess = new Timestamp(System.currentTimeMillis());
    }

    public User(String name, String email) {
        this.id = -1;
        this.name = name;
        this.email = email;
        this.hashedPassword = null;
        this.salt = null;
        this.createDate = null;
        this.recentAccess = null;
    }

    public User() {
        this.id = -1;
        this.name = null;
        this.email = null;
        this.hashedPassword = null;
        this.salt = null;
        this.createDate = null;
        this.recentAccess = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Timestamp getRecentAccess() {
        return recentAccess;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setRecentAccess(Timestamp recentAccess) {
        this.recentAccess = recentAccess;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", hashedPassword='" + hashedPassword + '\''
                + ", salt='" + salt + '\''
                + ", createDate=" + createDate
                + ", recentAccess=" + recentAccess
                + '}';
    }

    public boolean isDefault() {
        return id == -1
                && hashedPassword == null
                && salt == null
                && createDate == null
                && recentAccess == null;
    }

    public void updateNameOrEmail(String field, String value) {
        if (field.equals("user_name")) {
            this.name = value;
        } else if (field.equals("user_email")) {
            this.email = value;
        }
    }
}
