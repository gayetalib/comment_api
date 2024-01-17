package sn.pts.comment.cantine;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "cantine_td_menu")
@SequenceGenerator(sequenceName = "seq_menu", name = "seq_menu", initialValue = 100)
public class MenuEntity {
    @Id
    @Column(name = "id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menu")
    private Long id;

    @ElementCollection
    private Set<String> dishes;

    @Column(name = "date")
    private Date date;
}
