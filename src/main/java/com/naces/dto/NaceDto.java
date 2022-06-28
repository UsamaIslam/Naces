package com.naces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaceDto {

    @Min(value = 6, message = "Order should be greater then 6 or equal to 6")
    private Long order;

    @Min(value = 1, message = "Level should be a number and its size should be 1")
    @Max(value = 1, message = "Level should be a number and its size should be 1")
    private Integer level;

    @NotBlank(message = "Code is mandatory and should be single character")
    @Size(min = 1, message = "Code size should be 1")
    @Size(max = 1, message = "Code size should be 1")
    private String code;

    private String parent;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "This item includes is mandatory")
    private String itemInclude;

    private String itemAlsoInclude;

    private String ruling;

    private String itemExclude;

    @NotBlank(message = "Reference is mandatory and should be single character")
    @Size(min = 1, message = "Reference size should be 1")
    @Size(max = 1, message = "Reference size should be 1")
    private String reference;

    private Timestamp createDateTime;

    private Timestamp updatedDateTime;

}
