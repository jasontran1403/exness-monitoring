package com.foundation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RealtimeItemDto {
	private String name;
	private double value;
	private String candle;
	private double lot;
	private double spread;
	private int type;
	private int magic1;
	private int magic2;
	private boolean isRunning;
	private String time;
}
