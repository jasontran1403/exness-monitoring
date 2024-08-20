package com.foundation.service;

import java.util.List;
import java.util.Optional;

import com.foundation.dto.ExnessRequest;
import com.foundation.dto.ResultsDto;
import com.foundation.entity.Exness;

public interface ExnessService {
	List<ResultsDto> getAllExness();
	Optional<Exness> findByExness(String exnessId);
}
