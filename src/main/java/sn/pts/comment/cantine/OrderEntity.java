package sn.pts.comment.cantine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cantine_td_order")
@SequenceGenerator(name = "seq_order", sequenceName = "seq_order", initialValue = 100)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Column(name = "dish", length = 20)
    private String dish;

    @Column(name = "date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private OrderStatus status;

    @Column(name = "for_employee")
    private boolean forEmployee = true;

    @Column(name = "employee_name", length = 100)
    private String employeeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;
}