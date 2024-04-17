package com.easy.tour.entity.User;

import com.easy.tour.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity
        implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER  , cascade = CascadeType.ALL)
    private List <Role> roles = new ArrayList<>();

    public User() {
        this.uuid = UUID.randomUUID().toString();
    }

    public User (String email , String password , List<Role> roles) {
        this.email = email ;
        this.password = password ;
        this.roles = roles ;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     *This Method will return List role of User
     * Trả về danh sách các role của người dùng từ entity User
     * Ví dụ: Lấy tất cả Role của 1 User convert thành
     * đối tượng SimpleGrantedAuthority, sau đó lưu lại
     * trong List<GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }

    /**
     * TK còn hạn hay ko
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * TK có bị lock hay ko
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * Dùng để kiểm tra hạn sử dụng của 1 tài khoản
     * (ví dụ: một tài khoản có thời hạn sử dụng)
     * và trả về true hoặc false để cho phép
     * tk đó đc vào hệ thống hay ko
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Dùng để kích hoạt tài khoản người dùng
     * có thể đăng nhập và sử dụng hệ thống hay ko
     * Ex: Kiểm tra xem người dùng đã xác nhận email
     * hay chưa mà set thành True or false
     *
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
