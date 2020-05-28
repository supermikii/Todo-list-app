package model.todolist;


import java.util.*;

public class User {

    private String username;
    private String password;
    public boolean login;


    public User(String input, String password, boolean login) {
        this.username = input;
        this.password = password;
        this.login = login;
    }


    public User() {
        this.username = "";
        this.password = "";
        login = false;
    }

    public boolean getLogin() {
        return login;
    }


//    public void setUsername(String input) {
//        this.username = input;
//    }

    public void setLoginTrue() {
        this.login = true;
    }


    public String getPassword() {
        return password;
    }

//    public void setPassword(String input) {
//        this.password = input;
//    }


    public String getUserName() {
        return username;
    }


    public void setLoginFalse() {
        login = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            User user = (User) o;
            return Objects.equals(username, user.username);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
