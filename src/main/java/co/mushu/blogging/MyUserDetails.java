package co.mushu.blogging;

import co.mushu.blogging.models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class MyUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> roles;
    private Boolean isActive;

    public MyUserDetails(Users users){
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.roles = Arrays.stream(users.getRoleList().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.isActive = users.getActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }
}
