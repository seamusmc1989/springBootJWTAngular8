package com.angularBootRef.springBootPortfolio.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto implements IObjectDto {


	private Long id;
	private String comment;
	private Long rating;


//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeFormatUtils.DATE_TIME_FORMAT)
//	private LocalDateTime createdDate;

}
