package com.mastercard.cityconnectivity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import com.mastercard.cityconnectivity.service.CityConnectivityService;

@SpringBootTest
class CityconnectivityApplicationTests {

	@Autowired
    CityConnectivityService cityConnectivityService;
	
	@Test
	public void connectivity_Test1(){
		assertTrue(cityConnectivityService.searchConnectivity("Boston", "Newark"), "YES");
	}

	@Test
	public void connectivity_Test2(){
		assertTrue(cityConnectivityService.searchConnectivity("Boston", "Philadelphia"), "YES");
	}
	
	@Test
	public void connectivity_Test3(){
		assertFalse(cityConnectivityService.searchConnectivity("Philadelphia", "Albany"), "NO");
	}
}
