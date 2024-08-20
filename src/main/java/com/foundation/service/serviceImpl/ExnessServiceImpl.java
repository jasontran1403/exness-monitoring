package com.foundation.service.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.dto.ResultsDto;
import com.foundation.entity.Exness;
import com.foundation.entity.ExnessRepository;
import com.foundation.service.ExnessService;

@Service
public class ExnessServiceImpl implements ExnessService{
	@Autowired
	ExnessRepository exRepo;

	@Override
	public List<ResultsDto> getAllExness() {
		// TODO Auto-generated method stub
		List<Exness> results = exRepo.findAll();
		List<ResultsDto> resultsDto = new ArrayList<>();
		
		for (Exness exness : results) {
			if (exness.getExness().equals("1")) {
				continue;
			}
			ResultsDto item = new ResultsDto();
			item.setName(exness.getName());
			item.setExness(exness.getExness());
			item.setServer(exness.getServer());
			item.setBalance(exness.getBalance());
			item.setPassword(exness.getPassword());
			item.setLot(exness.getLot());
			item.setRate(exness.getReason());
			item.setStatus(exness.getStatus());
			
			if (exness.getServer().contains("XMGlobal")) {
				item.setCurrency("USD");
			} else {
				item.setCurrency("Cent");
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

			// Chuyển đổi timestamp thành đối tượng Date
			Date date = new Date(exness.getLatestUpdated() * 1000); // *1000 để đổi về milliseconds

			// Chuyển đối tượng Date thành chuỗi với định dạng "yyyy-MM-dd"
			String formattedDate = dateFormat.format(date);
			
			item.setTime(formattedDate);
			
			resultsDto.add(item);
		}
		return resultsDto;
	}

	@Override
	public Optional<Exness> findByExness(String exnessId) {
		// TODO Auto-generated method stub
		return exRepo.findByExness(exnessId);
	}

}
