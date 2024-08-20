package com.foundation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExnessRequest {

    @NotNull(message = "Exness không được để trống")
    private String exness;

    @NotNull(message = "Server không được để trống")
    private String server;

    @NotNull(message = "Password không được để trống")
    private String password;

    @NotNull(message = "Name không được để trống")
    private String name;

    private double lot;

    @NotNull(message = "Rate không được để trống")
    private String rate;

    @NotNull(message = "Refferal không được để trống")
    private String refferal;
}
