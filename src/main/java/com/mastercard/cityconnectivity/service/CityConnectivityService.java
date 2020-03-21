package com.mastercard.cityconnectivity.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mastercard.cityconnectivity.bean.CityMappingBean;

@Component
public class CityConnectivityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityConnectivityService.class);

	@Autowired
	CityMappingBean cityMappingBean;

	/**
	 * It will find all possible straight destinations for a Origin, and add
	 * them to all possible Origin for search.
	 * Then, it Iterates all Origins including original origin and look up if destination exist or not.
	 * 
	 * @param origin
	 * @param destination
	 * @return boolean
	 */
	public boolean searchConnectivity(String origin, String destination) {
		boolean connectivityFound = false;
		Set<String> allOrigins = findAllPossibleOrigin(origin.toUpperCase());
		if (allOrigins != null && !allOrigins.isEmpty()) {
			for (String originIter : allOrigins) {
				if (cityMappingBean.getCityToCityMapping().get(originIter) != null) {
					connectivityFound = cityMappingBean.getCityToCityMapping().get(originIter).stream()
							.anyMatch(city -> city.equalsIgnoreCase(destination));
					if (connectivityFound) {
						break;
					}
				}
			}
		}
		LOGGER.debug("does " + origin + " to " + destination + " have connectivity?[" + connectivityFound + "]");
		return connectivityFound;
	}

	/**
	 * Find all straight destinations from original. 
	 * then original destination to their destination;
	 * @param origin
	 * @return
	 */
	private Set<String> findAllPossibleOrigin(String origin) {
		Set<String> allOrigins = new HashSet<String>();
		allOrigins.add(origin);
		if (cityMappingBean.getCityToCityMapping().get(origin) != null) {
			Set<String> oneToOneOriginDestinationMapping = cityMappingBean.getCityToCityMapping().get(origin);
			allOrigins.addAll(oneToOneOriginDestinationMapping);
			allOrigins.addAll(
					oneToOneOriginDestinationMapping.stream().map(x -> cityMappingBean.getCityToCityMapping().get(x))
							.flatMap(x -> x.stream()).collect(Collectors.toSet()));
		}
		return allOrigins;
	}

}
