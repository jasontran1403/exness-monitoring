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
	private double balance;
	private double lot;
	private double equity;
	private List<RealtimeItemDto> listData;
}
