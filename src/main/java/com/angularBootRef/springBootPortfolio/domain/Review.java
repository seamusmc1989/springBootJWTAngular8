package com.angularBootRef.springBootPortfolio.domain;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review extends AuditModel implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Long rating;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Car car;

}
