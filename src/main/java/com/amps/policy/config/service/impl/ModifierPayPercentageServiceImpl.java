package com.amps.policy.config.service.impl;

import com.amps.policy.config.model.ModifierPayPercentage;
import com.amps.policy.config.repository.ModifierPayPercentageRepository;
import com.amps.policy.config.service.ModifierPayPercentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModifierPayPercentageServiceImpl implements ModifierPayPercentageService{
	
	@Autowired 
	ModifierPayPercentageRepository modifierPayPercentageRepository;

	@Override
	public List<ModifierPayPercentage> ModifierPayPercentageData(Integer Key) {
		// TODO Auto-generated method stub
		return modifierPayPercentageRepository.ModifierPayPercentageData(Key);
	}
}
