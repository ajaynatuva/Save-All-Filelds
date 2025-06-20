package com.amps.policy.config.service;

import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.model.TaxId;

import java.util.List;

public interface TaxIdService {
	List<TaxId> getTaxIdOfPolicy(int policyId);

	void saveTaxIdData(List<TaxId> taxIdList);

	void deleteByTaxId(List<TaxId> taxIdList);

	void uploadTaxId(List<TaxId> TaxId);

	List<TaxId> loadDataToTarget(Integer policyId);

	List<TaxId> getTaxIdGroupedDetails(Integer policyId);

	void saveNewTaxIDChanges(ChangesDTO changesDTO);
}
