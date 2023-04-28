package ru.panic.lapayment.template.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
@Data
public class User implements UserDetails {
    @JsonIgnore
    @Id
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Date registeredAt;
    private String token;
    private Double tron_balance;
    private Double bitcoin_balance;
    private Double ethereum_balance;
    private Double matic_balance;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
