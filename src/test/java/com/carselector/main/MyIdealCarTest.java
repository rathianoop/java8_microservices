package com.carselector.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.carselector.exception.CarNotFoundException;
import com.carselector.model.CarFeatures;


@RunWith(SpringJUnit4ClassRunner.class)
public class MyIdealCarTest {
	
	/*
	 * Mockito could have been used here if multiple layers were involved. 
	 */
	
	MyIdealCar toTest;
	List<CarFeatures> carFeaturesList;
	CarFeatures carFeatures_hondaCRV, carFeatures_fordEscape, carFearues_hundaiSantaFe, carFeatures_mazdaCX5, carFeatures_subaru_forester;

	@Before
	public void setUp()
	{
		carFeaturesList = new ArrayList<CarFeatures>();
		toTest = new MyIdealCar();
		carFeatures_hondaCRV = new CarFeatures.Builder().make("Honda").model("CRV")
				.color("Green").year(2016).price(BigDecimal.valueOf(23845)).tccRating(BigDecimal.valueOf(8)).hwyMPG(BigDecimal.valueOf(33)).build();
		carFeatures_fordEscape = new CarFeatures.Builder().make("Ford").model("Escape")
				.color("Red").year(2017).price(BigDecimal.valueOf(23590)).tccRating(BigDecimal.valueOf(7.8)).hwyMPG(BigDecimal.valueOf(32)).build();
		carFearues_hundaiSantaFe = new CarFeatures.Builder().make("Hundai").model("Santa Fe")
				.color("Grey").year(2016).price(BigDecimal.valueOf(24950)).tccRating(BigDecimal.valueOf(8)).hwyMPG(BigDecimal.valueOf(27)).build();
		carFeatures_mazdaCX5 = new CarFeatures.Builder().make("Mazda").model("CX5")
				.color("Red").year(2017).price(BigDecimal.valueOf(21795)).tccRating(BigDecimal.valueOf(8)).hwyMPG(BigDecimal.valueOf(35)).build();
		carFeatures_subaru_forester = new CarFeatures.Builder().make("Subaru").model("Forester")
				.color("Blue").year(2016).price(BigDecimal.valueOf(22395)).tccRating(BigDecimal.valueOf(8.4)).hwyMPG(BigDecimal.valueOf(32)).build();
		
		carFeaturesList.add(carFeatures_hondaCRV);
		carFeaturesList.add(carFeatures_fordEscape);
		carFeaturesList.add(carFearues_hundaiSantaFe);
		carFeaturesList.add(carFeatures_mazdaCX5);
		carFeaturesList.add(carFeatures_subaru_forester);
		
	}
	
	@Test
	public void test_newestVehicleInOrder()
	{
		List<CarFeatures> carFeatureList = toTest.getCarSortedByYear(carFeaturesList);
		CarFeatures results = carFeatureList.stream().findFirst().get();
		assertEquals("Ford", results.getMake());
		assertEquals("Escape", results.getModel());	
		assertEquals(Integer.valueOf(2017), results.getYear());		

	}
	
	@Test
	public void test_vehicleInAlphabeticalOrder()
	{
		List<CarFeatures> carFeatureList = toTest.getCarSortedByName(carFeaturesList);
		assertEquals("Ford" , carFeatureList.stream().findFirst().get().getMake());
		assertEquals("Subaru" , carFeatureList.get(carFeatureList.size()-1).getMake());
		assertEquals("Honda", carFeatureList.get(1).getMake());
		assertEquals("CRV", carFeatureList.get(1).getModel());
		assertEquals("Santa Fe", carFeatureList.get(2).getModel());
		
	}
	
	@Test
	public void test_vehicleListByPrice_asc()
	{
		List<CarFeatures> carFeatureList = toTest.getCarSortedByPrice(carFeaturesList);
		assertEquals(BigDecimal.valueOf(21795) , carFeatureList.stream().findFirst().get().getPrice());
		assertEquals("Mazda" , carFeatureList.stream().findFirst().get().getMake());
		assertEquals("Hundai" , carFeatureList.get(carFeatureList.size()-1).getMake());
		assertEquals("Santa Fe" , carFeatureList.get(carFeatureList.size()-1).getModel());
		assertEquals(BigDecimal.valueOf(24950) , carFeatureList.get(carFeatureList.size()-1).getPrice());
		
	}
	
	@Test
	public void test_vehicleByBestValue()
	{
		List<CarFeatures> carFeatureList = toTest.getCarSortedByBestValue(carFeaturesList);
		assertEquals(BigDecimal.valueOf(22395) , carFeatureList.stream().findFirst().get().getPrice());
		assertEquals("Subaru" , carFeatureList.stream().findFirst().get().getMake());
		assertEquals("Forester" , carFeatureList.stream().findFirst().get().getModel());
		assertEquals("Escape" , carFeatureList.get(carFeatureList.size()-1).getModel());
		assertEquals(BigDecimal.valueOf(23590) , carFeatureList.get(carFeatureList.size()-1).getPrice());
		assertEquals("Mazda", carFeatureList.get(1).getMake());
		assertEquals("CX5", carFeatureList.get(1).getModel());
		
	}
	
	@Test
	public void test_getFuleConsumptionForDistance()
	{
		BigDecimal averageFuelConsumption_CRV  = toTest.getFuleConsumption(carFeaturesList, BigDecimal.valueOf(10000), "CRV");
		assertEquals(BigDecimal.valueOf(303.03) , averageFuelConsumption_CRV);
		BigDecimal averageFuelConsumption_subaru  = toTest.getFuleConsumption(carFeaturesList, BigDecimal.valueOf(11000), "Forester");
		assertEquals(BigDecimal.valueOf(343.75) , averageFuelConsumption_subaru);
		
	}
	
	@Test(expected=CarNotFoundException.class)
	public void test_getFuleConsumptionForDistance_exception()
	{
		BigDecimal averageFuelConsumption_subaru  = toTest.getFuleConsumption(carFeaturesList, BigDecimal.valueOf(10000), "Subaru");
	}
	
	@Test
	public void test_getRandomCarFromTheList()
	{
		CarFeatures randomCar  = toTest.getRandomCar(carFeaturesList);
		assertNotNull(randomCar);
	}
	
	@Test
	public void test_getMPGByYearOfCar()
	{
		Map<Integer, BigDecimal> averageMpgPerYear  = toTest.getAverageMPGByYear(carFeaturesList);
		for(Integer year :  averageMpgPerYear.keySet())
		{
			if(year == 2016)
			{
				assertEquals("30.67", String.valueOf(averageMpgPerYear.get(year)));
			}
			else if(year == 2017)
			{
				assertEquals("33.50", String.valueOf(averageMpgPerYear.get(year)));
			}
		}
		
	}
	
	
}
