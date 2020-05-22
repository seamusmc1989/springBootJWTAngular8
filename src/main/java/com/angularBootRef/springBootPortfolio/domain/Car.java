package com.angularBootRef.springBootPortfolio.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
//@ToString
public class Car extends AuditModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="the make field should not be null or empty")
	private String make;

	@NotEmpty(message="the model field should not be null or empty")
	private String model;

	@NotEmpty(message="the engine field should be null or empty")
	private String engine;

	@NotEmpty(message="the transmission field should be null or empty")
	private String transmission;

	private String username;

	//With bi-directional oneToOne the only way to enable lazy is to use byteCode optimizer plugin in your maven
//	@OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
//	private Review review;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFormatUtils.DATE_TIME_FORMAT)
//	private LocalDateTime createdDate;

}
