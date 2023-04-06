package com.example.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}





/*
id: là một Integer đại diện cho id của người dùng trong cơ sở dữ liệu.
username: là một String đại diện cho tên đăng nhập của người dùng. Thuộc tính này được đánh dấu là unique và không được phép để trống (nullable = false).
password: là một String đại diện cho mật khẩu của người dùng. Thuộc tính này không được phép để trống (nullable = false).
fullName: là một String đại diện cho tên đầy đủ của người dùng. Thuộc tính này không được phép để trống (nullable = false).
roles: là một Set<Role> đại diện cho danh sách các vai trò của người dùng trong hệ thống. Mỗi người dùng có thể có nhiều vai trò và mỗi vai trò có thể được sử dụng bởi nhiều người dùng. Thuộc tính này được đánh dấu với @ManyToMany để biểu thị quan hệ n-n giữa User và Role, và được định nghĩa một bảng trung gian (join table) với các cột user_id và role_id. Thuộc tính fetch được đặt là FetchType.EAGER để đảm bảo các vai trò của người dùng được tải vào bộ nhớ khi người dùng được truy vấn, và thuộc tính cascade được đặt là CascadeType.ALL để đảm bảo các thao tác CRUD trên người dùng cũng được áp dụng trên các vai trò của người dùng.
* */
