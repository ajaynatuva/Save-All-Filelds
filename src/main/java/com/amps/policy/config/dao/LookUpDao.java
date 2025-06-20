package com.amps.policy.config.dao;

import com.amps.policy.config.dto.SubSpecDTO;
import com.amps.policy.config.model.CCIDeviations;
import com.amps.policy.config.model.CCIDeviationsDTO;
import com.amps.policy.config.model.ModLookUp;

import java.util.Date;
import java.util.List;

public interface LookUpDao {
    List<SubSpecDTO> getSubSpecData();
    void updateDeviationsInCCI(Integer Key, String column_i, String column_ii);
    List<ModLookUp>getModLkpData();

}
