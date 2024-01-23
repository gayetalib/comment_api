package sn.pts.comment.entity.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> implements Serializable {
    private static final String SYSTEM = "system";

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    @JsonIgnore
    protected String createdBy = SYSTEM;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "modified_by")
    @JsonIgnore
    protected String lastModifiedBy = SYSTEM;

    @UpdateTimestamp
    @Column(name = "modified_date")
    @JsonIgnore
    protected LocalDateTime lastModifiedDate;

    @Column(name = "is_enabled")
    private boolean enabled = true;

    @Column(name = "is_deleted")
    private boolean deleted = false;
}
