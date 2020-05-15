package com.angularBootRef.springBootPortfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ROLE")
@Getter
@Setter
public class UserRole {

	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName="id")
	private User user;

	@Column(name = "ROLE")
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

}
