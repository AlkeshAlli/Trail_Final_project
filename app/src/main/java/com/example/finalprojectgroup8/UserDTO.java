package com.example.finalprojectgroup8;

public class UserDTO {


    public Integer id;

    public String user_id;

    public String role_name;

    public String email;

    public String intro;

    public UserDTO() {
    }

    public UserDTO(Integer id, String user_id, String role_name, String email, String intro) {
        this.id = id;
        this.user_id = user_id;
        this.role_name = role_name;
        this.email = email;
        this.intro = intro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", role_name='" + role_name + '\'' +
                ", email='" + email + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }

}
