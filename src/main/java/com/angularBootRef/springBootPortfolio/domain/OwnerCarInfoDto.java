package com.angularBootRef.springBootPortfolio.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A DTO for the OwnerCarInfo.
 */
@SqlResultSetMapping(
        name="ownerCarInfoQueryMapping",
        classes={
                @ConstructorResult(
                        targetClass= OwnerCarInfoDto.class,
                        columns={
                                @ColumnResult(name="owner_id", type = Long.class),
                                @ColumnResult(name="first_name", type = String.class),
                                @ColumnResult(name="last_name", type = String.class),
                                @ColumnResult(name="make", type = String.class)
                        }
                )
        }
)
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OwnerCarInfoDto implements Serializable {

    @Id
    private Long ownerId;

    private String firstName;
    private String lastName;
    private String make;

}
