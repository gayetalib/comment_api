package sn.pts.comment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "bss_td_comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(sequenceName = "seq_comment", name = "seq_comment", initialValue = 100)
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comment")
    @Id
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    @Column(name = "date")
    private Date date;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}