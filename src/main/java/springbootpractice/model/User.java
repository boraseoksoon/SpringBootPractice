package springbootpractice.model;

/**
 * Created by seoksoonjang on 2017. 3. 23..
 */
public class User {
    String userId;
    String email;
    String password;
    String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "user object description : [userId : " + getUserId() + " password : " +
        getPassword() + " name : " + getName() + " email : " + getEmail() + "]";
    }
}
