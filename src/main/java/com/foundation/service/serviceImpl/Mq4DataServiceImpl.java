package com.foundation.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.dto.RealtimeDataDto;
import com.foundation.dto.RealtimeDataProjection;
import com.foundation.dto.RealtimeItemDto;
import com.foundation.entity.Mq4Data;
import com.foundation.repository.Mq4DataRepository;
import com.foundation.service.Mq4DataService;

@Service
public class Mq4DataServiceImpl implements Mq4DataService {
	@Autowired
	Mq4DataRepository mq4Repo;

	@Override
	public List<RealtimeDataProjection> getRealtimeData() {
		// TODO Auto-generated method stub
		return mq4Repo.getAllRealtimeData();
	}

	@Override
	public RealtimeDataDto getRealtimeDataByExnessId(String exnessId) {
		// TODO Auto-generated method stub
		List<Mq4Data> listDataFromExness = mq4Repo.getRealtimeDataByExnessId(exnessId);
		RealtimeDataDto results = new RealtimeDataDto();
		List<RealtimeItemDto> dataItem = new ArrayList<>();
		for (Mq4Data item : listDataFromExness) {
			RealtimeItemDto itemConvert = new RealtimeItemDto();
			itemConvert.setName(item.getCurrencyName());
			itemConvert.setValue(item.getCurrencyEquity());
			String candle = "";
			if (Integer.parseInt(item.getCurrentCandle()) == 30) {
				candle = "M30";
			} else if (Integer.parseInt(item.getCurrentCandle()) == 60) {
				candle = "H1";
			} else if (Integer.parseInt(item.getCurrentCandle()) == 240) {
				candle = "H4";
			} else if (Integer.parseInt(item.getCurrentCandle()) == 1440) {
				candle = "D1";
			}
			itemConvert.setCandle(candle);
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

			// Chuyển đổi timestamp thành đối tượng Date
			Date date = new Date(item.getLastestUpdated() * 1000); // *1000 để đổi về milliseconds

			// Chuyển đối tượng Date thành chuỗi với định dạng "yyyy-MM-dd"
			String formattedDate = dateFormat.format(date);
			itemConvert.setTime(formattedDate);

			if (item.getCurrencyEquity() > 0) {
				itemConvert.setType(0);
			} else {
				double ratio = Math.abs(item.getCurrencyEquity()) / item.getCurrentBalance();

				if (ratio < 0.1) {
					itemConvert.setType(1);
				} else if (ratio >= 0.1 && ratio <= 0.2) {
					itemConvert.setType(2);
				} else {
					itemConvert.setType(3);
				}
			}

			dataItem.add(itemConvert);
		}

		results.setExnessId(exnessId);
		results.setRealtimeData(dataItem);

		return results;
	}

	@Override
	public String getRealtimeCandle(String exnessId, String currencyName) {
		// TODO Auto-generated method stub
		Optional<Mq4Data> data = mq4Repo.findExistedData(exnessId, currencyName);
		if (data.isPresent()) {
			String result = "";
			int number = Integer.parseInt(data.get().getCurrentCandle());
			if (number == 30) {
				result = "M30";
			} else if (number == 60) {
				result = "H1";
			} else if (number == 240) {
				result = "H4";
			}else if (number == 1440) {
				result = "D1";
			}
			return result;
		}
		throw new RuntimeException("Không có dữ liệu của cặp " + currencyName + " từ ExnessId#" + exnessId);
	}

	@Override
	public String getUpcomingCandle(String exnessId, String currencyName) {
		// TODO Auto-generated method stub
		Optional<Mq4Data> result = mq4Repo.findExistedData(exnessId, currencyName);
		if (result.isPresent()) {
			return result.get().getUpcomingCandle();
		}
		throw new RuntimeException("Không có dữ liệu của cặp " + currencyName + " từ ExnessId#" + exnessId);
	}

	@Override
	public long getLatestTransaction() {
		// TODO Auto-generated method stub
		return mq4Repo.getLatestRealtimeData();
	}

}
