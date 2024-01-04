package sn.pts.comment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "bss_td_comment")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", unique = true)
    private String author;

    @Column(name = "text")
    @Lob
    private String text;

    @Column(name = "date")
    private Date date;
}