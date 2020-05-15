package com.angularBootRef.springBootPortfolio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Owner extends AuditModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ownerId;

	@NotEmpty(message="the firstName field should not be null or empty")
	private String firstName;
	@NotEmpty(message="the lastname field should not be null or empty")
	private String lastName;
	@NotEmpty(message="the username field should not be null or empty")
	private String username;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

}
