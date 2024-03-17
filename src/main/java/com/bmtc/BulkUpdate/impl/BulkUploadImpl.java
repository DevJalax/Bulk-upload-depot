package com.bmtc.BulkUpdate.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmtc.BulkUpdate.Repo.bulkUploadRepo;
import com.bmtc.BulkUpdate.entity.BulkDepot;
import com.bmtc.BulkUpdate.entity.BulkDepotRequestDTO;
import com.bmtc.BulkUpdate.service.BulkUpdateService;


@Service
public class BulkUploadImpl implements BulkUpdateService {

	@Autowired
	private bulkUploadRepo repo;
	
	//@Autowired
	BulkDepot data = new BulkDepot();
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BulkUploadImpl.class);

	
	public void bulkUpload(BulkDepotRequestDTO request) {
		
		List<String> depotList = new ArrayList<>();
		List<String> csvDataList = new ArrayList<>();
		Set<String> uniqueRecords = new HashSet<>();
		
		
		depotList.add("Depot1");
		depotList.add("Depot2");
		depotList.add("Depot3");
		depotList.add("Depot4");
		depotList.add("Depot5");
		depotList.add("Depot6");
		
		try {
			
			// check in db whether depot and counter name is same as master table
			// new means enter into db else dont update
		   
			
	   if(!request.getRegNoOrCounterNames().getOriginalFilename().endsWith(".csv")) {
		   throw new Exception("Invalid file type. Please upload only csv file");
	   }
	   
				if(!request.getType().equalsIgnoreCase("counter name") && !request.getType().equalsIgnoreCase("vehicle registration number")) {
					throw new Exception("Invalid type. Please enter type as either counter name or vehicle registration number ");
				}
				
				    setMasterDetails();
				    setDTLDetails();
					setCsvDetails(request);
				
					// repo.master entity save
					// repo.DTL entity save
							
		} catch(Exception e) {
			LOGGER.error("Exception while bulkUpload method : " + e.getMessage());
		}
		
		
	}
	
	      private static boolean hasNonBlankCells(CSVRecord csv) {
	         for (String cell : csv) {
	            if (cell != null && !cell.trim().isEmpty()) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    // set all master table values
	    public void setMasterDetails() {
	    	
	    }
	    
	    // set all DTL table values
	    public void setDTLDetails() {
	    	
	    }
	      
	    public void setCsvDetails(BulkDepotRequestDTO request) {
	    
	    	List<String> csvDataList = new ArrayList<>();
			Set<String> uniqueRecords = new HashSet<>();
			Map<String, Integer> frequencyMap = new HashMap<>();
	    	
	    	try {
	    		int total = 0 , tot_val_data = 0 , tot_inval_data = 0;
	    		
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getRegNoOrCounterNames().getInputStream(),"UTF-8"));
				CSVParser csvParser = new CSVParser(reader , CSVFormat.DEFAULT.withTrim());
				List<CSVRecord> records = csvParser.getRecords();
				
				for (CSVRecord csv : records) {
					
					if(csv.size()>1) {
						throw new Exception("Please enter data in 1 column way " + csvParser.getCurrentLineNumber());
					}
					
					if(hasNonBlankCells(csv)) {
						total++;
					}
					
					String csvRow = String.join(",", csv);						
					
                     //non repeating in csv and not existing in db
					if (!uniqueRecords.contains(csvRow)) {
			            uniqueRecords.add(csvRow);
			            csvDataList.add(csvRow);
			            tot_val_data = uniqueRecords.size();
			            }
			
					for(String value : csv) {
						frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
					}
					
			}
				
			    // set total
			    // set val_data
			    // set inval_data
			    // set frequency map in master
			    
				} catch (IOException e) {
				e.printStackTrace();
			}
	    	catch (Exception e) {
				e.printStackTrace();
			}
	    
	    }
	}

