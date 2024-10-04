package com.solidwall.tartib.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solidwall.tartib.entities.base.AbstractBaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity extends AbstractBaseEntity implements UserDetails {

  @Column(name = "firstname", length = 50, nullable = false)
  private String firstname;

  @Column(name = "lastname", length = 50, nullable = false)
  private String lastname;

  @Column(name = "username", length = 50, nullable = false)
  private String username;

  @Column(name = "email", unique = false, nullable = false)
  private String email;

  @Column(name = "password", nullable = false, length = 60)
  private String password;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnore
  @JsonBackReference
  private List<UserRoleEntity> userRoles;

  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles != null ? userRoles.stream()
            .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
            .collect(Collectors.toList()) : Collections.emptyList();
    }

  @Column(name = "is_account_non_expired", nullable = true)
  private boolean isAccountNonExpired = true;

  @Column(name = "account_non_locked", nullable = true)
  private boolean accountNonLocked = true;

  @Column(name = "credentials_non_expired", nullable = true)
  private boolean credentialsNonExpired = true;

  @Column(name = "enabled", nullable = true)
  private boolean enabled = true;

}
