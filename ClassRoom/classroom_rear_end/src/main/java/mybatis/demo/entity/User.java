package mybatis.demo.entity;


import mybatis.demo.security02.model.Role;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EntityScan
public class User implements UserDetails {
    static final long serialVersionUID = 1L;

    //用户id
    private long uid;
    //用户学号
    private String username;
    //用户姓名
    private String uname;
    //用户性别
    private String usex;
    //用户密码
    private String password;
    //学校
    private String school;
    //电话
    private String phone;
    //用户的角色
    private List<Role> roles;

    private Boolean enabled;

    private Boolean locked;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public long getuid() {
        return uid;
    }

    public void setuid(long uid) {
        this.uid = uid;
    }

    public String getuname() {
        return uname;
    }

    public void setuname(String uname) {
        this.uname = uname;
    }

    public String getusex() {
        return usex;
    }

    public void setusex(String usex) {
        this.usex = usex;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
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
}
