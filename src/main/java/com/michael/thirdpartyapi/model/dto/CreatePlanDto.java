package com.michael.thirdpartyapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePlanDto {

    @NotNull(message = "Plan name cannot be null")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Interval cannot be null")
    @JsonProperty("interval")
    private String interval;

    @NotNull(message = "Amount cannot be null")
    @JsonProperty("amount")
    @Digits(integer = 6, fraction = 2)
    private Integer amount;
}