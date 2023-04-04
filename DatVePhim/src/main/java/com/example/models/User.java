package com.example.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    public User() {}

    public User(Integer id, String username, String password, String fullName, Set<Role> roles){
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}





/*
id: là một Integer đại diện cho id của người dùng trong cơ sở dữ liệu.
username: là một String đại diện cho tên đăng nhập của người dùng. Thuộc tính này được đánh dấu là unique và không được phép để trống (nullable = false).
password: là một String đại diện cho mật khẩu của người dùng. Thuộc tính này không được phép để trống (nullable = false).
fullName: là một String đại diện cho tên đầy đủ của người dùng. Thuộc tính này không được phép để trống (nullable = false).
roles: là một Set<Role> đại diện cho danh sách các vai trò của người dùng trong hệ thống. Mỗi người dùng có thể có nhiều vai trò và mỗi vai trò có thể được sử dụng bởi nhiều người dùng. Thuộc tính này được đánh dấu với @ManyToMany để biểu thị quan hệ n-n giữa User và Role, và được định nghĩa một bảng trung gian (join table) với các cột user_id và role_id. Thuộc tính fetch được đặt là FetchType.EAGER để đảm bảo các vai trò của người dùng được tải vào bộ nhớ khi người dùng được truy vấn, và thuộc tính cascade được đặt là CascadeType.ALL để đảm bảo các thao tác CRUD trên người dùng cũng được áp dụng trên các vai trò của người dùng.
* */
