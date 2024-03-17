package com.bmtc.BulkUpdate.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BULK_DEPOT_DETAILS")
public class BulkDepot implements Serializable {

	private static final long serialVersionUID = 8556324048771797598L;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UPI_CC_DET_SEQ")
//	@SequenceGenerator(name = "UPI_CC_DET_SEQ", sequenceName = "UPI_CC_DET_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="Depot")
    private String Depot;
	
    @Column(name="Typ")
	private String Type;
	
    @Column(name="RegNoOrCounterNames")
	private String RegNoOrCounterNames;
	
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
	public String getRegNoOrCounterNames() {
		return RegNoOrCounterNames;
	}
	public void setRegNoOrCounterNames(String regNoOrCounterNames) {
		RegNoOrCounterNames = regNoOrCounterNames;
	}
	
	
}

