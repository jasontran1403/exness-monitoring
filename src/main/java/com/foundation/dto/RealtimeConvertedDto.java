package com.foundation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RealtimeConvertedDto {
	private String exnessId;
	private String mt4Server;
	private String password;
	private String name;
	private double balance;
	private double lot;
	private String currency;
	private double equity;
	private int magic1;
	private int magic2;
	private String server;
	private String user;
	private boolean isRunning;
	private String status;
	private List<RealtimeItemDto> listData;
}
