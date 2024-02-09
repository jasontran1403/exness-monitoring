package com.foundation.service;

import java.util.List;

import com.foundation.dto.RealtimeDataDto;
import com.foundation.dto.RealtimeDataProjection;

public interface Mq4DataService {
	List<RealtimeDataProjection> getRealtimeData();
	String getRealtimeCandle(String exnessId, String currencyName);
	String getUpcomingCandle(String exnessId, String currencyName);
	RealtimeDataDto getRealtimeDataByExnessId(String exnessId);
	long getLatestTransaction();
}
