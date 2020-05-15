package com.angularBootRef.springBootPortfolio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Review extends AuditModel implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Long rating;

    @OneToOne
    @MapsId
    private Car car;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "car_id")
//    private Car car;

}
