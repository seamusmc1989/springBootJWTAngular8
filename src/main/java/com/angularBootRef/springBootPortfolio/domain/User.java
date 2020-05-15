package com.angularBootRef.springBootPortfolio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
//@Table(name = "user", uniqueConstraints = {
//	       @UniqueConstraint(columnNames = {"USERNAME"}) })
public class User implements Serializable {

	@Id
	@Column(name="id")
	private Long id;

	@Column(name="USERNAME")
	private String username;
	
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

//	@Column(name = "ROLE")
//	private String role;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD_HASH")
	private String passwordHash;
//
//	@Column(name = "LAST_LOGIN_DATE")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFormatUtils.DATE_TIME_FORMAT)
//	private LocalDateTime lastLoginDate;

	@JsonIgnore
	@OneToMany(mappedBy = "user", targetEntity = UserRole.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.OptimisticLock(excluded = true)
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL})
	private List<UserRole> roles;

}
