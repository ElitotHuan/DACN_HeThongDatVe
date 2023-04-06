package com.example.security;

import com.example.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(Integer id, String password, String userName,
                         Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                authorities
        );
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}





/*
Class UserPrinciple là một implementation của interface UserDetails trong Spring Security, đại diện cho thông tin người dùng được sử dụng trong việc xác thực và phân quyền.

Class UserPrinciple có các thuộc tính như sau:

id: kiểu Integer, là id của người dùng.
userName: kiểu String, là tên đăng nhập của người dùng.
password: kiểu String, là mật khẩu của người dùng được mã hóa.
roles: kiểu Collection<? extends GrantedAuthority>, là danh sách các quyền được cấp cho người dùng.
Ngoài ra, class UserPrinciple còn cài đặt các phương thức của interface UserDetails như:

getAuthorities(): trả về danh sách các quyền được cấp cho người dùng.
getPassword(): trả về mật khẩu của người dùng.
getUsername(): trả về tên đăng nhập của người dùng.
isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled(): trả về giá trị true để đánh dấu tài khoản của người dùng không hết hạn, không bị khóa, không hết hạn đăng nhập và đã được kích hoạt.
*/