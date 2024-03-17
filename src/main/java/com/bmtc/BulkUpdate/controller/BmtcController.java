package com.bmtc.BulkUpdate.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bmtc.BulkUpdate.entity.BulkDepotRequestDTO;

import com.bmtc.BulkUpdate.impl.BulkUploadImpl;

@RestController
@RequestMapping("/api")
public class BmtcController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BmtcController.class);
	
	@Autowired
	BulkUploadImpl bulkUploadImpl;
	
	@GetMapping(value="/get-csv")
	public ResponseEntity<?> handleGetCsv(){
		return ResponseEntity.ok("CSV data retrieval success");
	}
	
	@PostMapping(value="/upload-csv",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> handleCsvUpload(@RequestParam("Depot") String depot, @RequestParam("Type") String type, @RequestPart("Data") MultipartFile data) throws IOException {		
	LOGGER.info("Starting bulk upload controller {}");
	if(depot == null || depot.equals(" ")) {
		return ResponseEntity.badRequest().body("Depot value is null/ empty. It is a required field.");
	}
	if(type == null || type.equals(" ")) {
		return ResponseEntity.badRequest().body("Type value is null/ empty. It is a required field.");
	}
	if(data == null) {
		return ResponseEntity.badRequest().body("Csv file is not uploaded. It is required");
	}
	
	BulkDepotRequestDTO bulkDepotRequestDTO = new BulkDepotRequestDTO();
	bulkDepotRequestDTO.setDepot(depot);
	bulkDepotRequestDTO.setType(type);
	bulkDepotRequestDTO.setRegNoOrCounterNames(data);
	bulkUploadImpl.bulkUpload(bulkDepotRequestDTO);
	return ResponseEntity.ok("CSV data processed successfully.");
}
}
