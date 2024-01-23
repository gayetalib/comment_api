package sn.pts.comment.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sn.pts.comment.entity.Account;
import sn.pts.comment.entity.Role;
import sn.pts.comment.security.utils.SecurityUtils;
import sn.pts.comment.web.dto.response.auth.AuthenticatedUserInfosDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String username;

    private final String email;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;
    private final AuthenticatedUserInfosDTO details;


    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, AuthenticatedUserInfosDTO details) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.details = details;
    }

    public static UserDetailsImpl build(Account account) {
        //List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (Objects.nonNull(account.getUser().getRoles()) && !account.getUser().getRoles().isEmpty()) {
/*            roles = account.getUser().getRoles().stream()
                    .filter(Auditable::isEnabled)
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .toList();*/

            authorities = account.getUser().getRoles().stream()
                    //.filter(Auditable::isEnabled)
                    .map(Role::getPermissions).flatMap(Collection::stream)
                    .map(permission -> new SimpleGrantedAuthority(permission.getName().name()))
                    .collect(Collectors.toList());

        }

        AuthenticatedUserInfosDTO details = SecurityUtils.formatUser(account);


        return new UserDetailsImpl(
                account.getId(),
                account.getLogin(),
                account.getUser().getEmail(),
                account.getPassword(),
                authorities, details);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public AuthenticatedUserInfosDTO getDetails() {
        return details;
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
        return details.getAccountDetails().isEnabled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}