package com.angularBootRef.springBootPortfolio.dto;

import com.angularBootRef.springBootPortfolio.utils.DateTimeFormatUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto implements IObjectDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFormatUtils.DATE_TIME_FORMAT)
	private LocalDateTime createdDate;

}
