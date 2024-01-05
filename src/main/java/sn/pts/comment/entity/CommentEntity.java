package sn.pts.comment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "bss_td_comment")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    //@Column(name = "date")
    //private Date date;
}