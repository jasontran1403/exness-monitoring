package com.foundation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultsDto {
	private String exness;
	private String server;
	private String password;
	private String name;
	private double balance;
	private String currency;
	private String time;
	private double lot;
	private String rate;
	private String deviceServer;
	private String deviceUser;
	private String status;
}
