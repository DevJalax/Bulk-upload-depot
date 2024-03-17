package com.bmtc.BulkUpdate.entity;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvBindByName;

public class BulkDepotRequestDTO  {

	private String Depot;
	
	private String Type;
	
    @CsvBindByName(column="REGNO_OR_COUNTER")
	private MultipartFile RegNoOrCounterNames;
	
	public String getDepot() {
		return Depot;
	}
	public void setDepot(String depot) {
		Depot = depot;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public MultipartFile getRegNoOrCounterNames() {
		return RegNoOrCounterNames;
	}
	public void setRegNoOrCounterNames(MultipartFile regNoOrCounterNames) {
		RegNoOrCounterNames = regNoOrCounterNames;
		
	}
}

