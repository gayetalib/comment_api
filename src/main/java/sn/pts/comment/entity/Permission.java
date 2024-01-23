package sn.pts.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import sn.pts.comment.entity.enums.PermissionType;

import java.util.Objects;

@Entity
@Table(name = "PTS_TD_PERMISSION")
@SequenceGenerator(name = "seq_permission", initialValue = 100, allocationSize = 2, sequenceName = "seq_permission")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permission")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionType name;

    @Column(name = "description")
    private String description;

    @Column(name = "feature_code")
    private String featureCode;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Permission role = (Permission) o;
        return getId() != null && Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
