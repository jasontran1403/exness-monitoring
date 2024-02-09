package com.foundation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.foundation.dto.RealtimeConvertedDto;
import com.foundation.dto.RealtimeDataDto;
import com.foundation.dto.RealtimeDataProjection;
import com.foundation.service.Mq4DataService;


@Controller
public class HomeController {
	@Autowired
	Mq4DataService mq4Service;
	
	@GetMapping("/")
	public String index(Model model) {
		List<RealtimeConvertedDto> listResult = new ArrayList<>();
		List<RealtimeDataProjection> results = mq4Service.getRealtimeData();
		for (RealtimeDataProjection item : results) {
			RealtimeConvertedDto itemData = new RealtimeConvertedDto();
			itemData.setExnessId(item.getExnessId());
			itemData.setBalance(item.getBalance());
			itemData.setLot(item.getLot());
			itemData.setEquity(item.getEquity());

			RealtimeDataDto listData = mq4Service.getRealtimeDataByExnessId(item.getExnessId());
			
			itemData.setListData(listData.getRealtimeData());
			
			listResult.add(itemData);
			
		}
		model.addAttribute("result", listResult);
		return "index";
	}
}
