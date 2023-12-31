package me.niallmurray.slipstream.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  @OneToMany(fetch = FetchType.LAZY,
          cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
          mappedBy = "user")
  private Set<Authority> authorities = new HashSet<>();
  @Column()
  private String lastLogout;

  @Override
  public String toString() {
    return "User:" +
            " userId= " + userId +
            ", username= '" + username + '\'' +
            ", email= '" + email + '\'' +
            ", lastLogout= " + lastLogout;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;
    return Objects.equals(userId, user.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }
}
