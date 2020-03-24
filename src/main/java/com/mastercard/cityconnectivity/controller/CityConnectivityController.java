package com.mastercard.cityconnectivity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.cityconnectivity.service.CityConnectivityService;

import io.swagger.annotations.ApiOperation;

@RestController

public class CityConnectivityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityConnectivityController.class);
	
	@Autowired
	CityConnectivityService cityConnectivityService;

	@GetMapping("/connected")
	@ApiOperation(value="Lookup Cities Connectivity", 
				notes="Lookup Connectivity from origin to destination directly or indirectly",
				response=ResponseEntity.class)
	public ResponseEntity<String> checkCitiesConnected(@RequestParam(value="origin", required=true) String origin,
			@RequestParam(value="destination", required=true) String destination) {
		LOGGER.info("Checking Connectivity from ["+origin+ "]to["+destination+"]");
		
		//Validating required parameter has value, if not return bad request with Customized message
		if((origin == null || origin.trim().isEmpty()) || (destination == null || destination.trim().isEmpty())){
			return new ResponseEntity<String>("Missing Required Parammeter Origin or Destination", HttpStatus.BAD_REQUEST);
		}

		String returnMessage;
		if(cityConnectivityService.searchConnectivity(origin, destination)){
			returnMessage = "YES";
		}else{
			returnMessage = "NO";
		}
		return ResponseEntity.ok(returnMessage);
	}
}
