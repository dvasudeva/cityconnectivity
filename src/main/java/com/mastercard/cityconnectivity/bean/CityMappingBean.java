package com.mastercard.cityconnectivity.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CityMappingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityMappingBean.class); 
	
	@Value("classpath:input/CityRoute.txt")
	private Resource cityRoutes;

	private Map<String, Set<String>> cityToCityMapping;

	/**
	 * It builds Two way mapping of all origin and its Unique destinations straight connectivity
	 * @return Map<String, Set<String>> i.e. Map<CITY, Set<"CITY1", "CITY2", "CITY3">>
	 */
	@PostConstruct
	private Map<String, Set<String>> buildMapping() {
		cityToCityMapping = new HashMap<String, Set<String>>();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(cityRoutes.getInputStream()));
			String line;
			while ((line = bf.readLine()) != null) {
				String[] citiRowArray = line.split(",");
				addMapping(citiRowArray[0].trim().toUpperCase(), citiRowArray[1].trim().toUpperCase());
				addMapping(citiRowArray[1].trim().toUpperCase(), citiRowArray[0].trim().toUpperCase());
			}
			cityToCityMapping.entrySet().forEach(entry -> LOGGER.debug(entry.getKey() + " -" + entry.getValue()));
		}catch (IOException ioe) {
			LOGGER.error("Exception occurred hile Reading City Data file");
		}catch (Exception e) {
			LOGGER.error("General Exception occurred while Reading City Data file");
		}

		return cityToCityMapping;
	}

	/**
	 * It adds the mapping into Set
	 * @param fromCity
	 * @param toCity
	 */
	private void addMapping(String fromCity, String toCity) {
		Set<String> connectedCityList = cityToCityMapping.get(fromCity);
		if (connectedCityList == null) {
			connectedCityList = new HashSet<String>();
			cityToCityMapping.put(fromCity, connectedCityList);
		}
		connectedCityList.add(toCity);
	}

	public Map<String, Set<String>> getCityToCityMapping() {
		return cityToCityMapping;
	}
}
