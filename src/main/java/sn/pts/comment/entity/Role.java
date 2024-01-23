package sn.pts.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PTS_TD_ROLE")
@SequenceGenerator(name = "seq_role", initialValue = 100, allocationSize = 2, sequenceName = "seq_role")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", length = 50, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "derived_from")
    private Long derivedFrom;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PTS_TR_ROLE_PERMISSION", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
