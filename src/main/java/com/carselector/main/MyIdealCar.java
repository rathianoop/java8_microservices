package com.carselector.main;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.carselector.exception.CarNotFoundException;
import com.carselector.model.CarFeatures;

public class MyIdealCar {
	
	/*
	 * Spring injection/autowired could have been added here if more was needed from the called functionality. Calling service layer from here,
	 *  DAO layer if needed. 
	 */
	
	public List<CarFeatures> getCarSortedByYear(List<CarFeatures> carList)
	{
		carList.sort(Comparator.comparingInt(CarFeatures::getYear).reversed().thenComparing(CarFeatures :: getMake).thenComparing(CarFeatures :: getModel));
		return carList.stream().collect(toList());		
	}
	
	public List<CarFeatures> getCarSortedByName(List<CarFeatures> carList)
	{
		carList.sort(Comparator.comparing(CarFeatures::getMake).thenComparing(CarFeatures :: getModel));
		return carList.stream().collect(Collectors.toList());		
	}
	
	public List<CarFeatures> getCarSortedByPrice(List<CarFeatures> carList)
	{
		carList.sort(Comparator.comparing(CarFeatures::getPrice));
		return carList.stream().collect(toList());		
	}
	
	public List<CarFeatures> getCarSortedByBestValue(List<CarFeatures> carList)
	{
		carList.sort(Comparator.comparing(CarFeatures::getTccRating).reversed().thenComparing(CarFeatures :: getPrice).thenComparing(CarFeatures :: getHwyMPG));
		return carList.stream().collect(toList());		
	}
	
	public BigDecimal getFuleConsumption(List<CarFeatures> carList, BigDecimal distance, String carModel)
	{
		Optional<CarFeatures> car = carList.stream().filter(x -> x.getModel().equalsIgnoreCase(carModel)).findFirst();
		if(car.isPresent())
			return BigDecimal.valueOf(distance.doubleValue()/car.get().getHwyMPG().doubleValue()).setScale(2,RoundingMode.HALF_UP);
		else
			throw new CarNotFoundException(String.format("Car Entered '%s' not found.", carModel));
	}
	
	public CarFeatures getRandomCar(List<CarFeatures> carList)
	{
		return carList.stream().findAny().get();		
	}
	
	public Map<Integer, BigDecimal> getAverageMPGByYear(List<CarFeatures> carList)
	{
		Map<Integer, BigDecimal> averageMPGPerYear = new HashMap<Integer, BigDecimal>();
		Map<Integer, List<CarFeatures>> carFeaturesMapByYear = carList.stream().collect(groupingBy(CarFeatures::getYear));
		
		Set<Integer> yearSet = carFeaturesMapByYear.keySet();
		for(Integer year : yearSet)
		{
			List<CarFeatures> carFeatureList = carFeaturesMapByYear.get(year);
			try 
			{
			double averageMPGForYear = carFeaturesMapByYear.get(year).stream().
					mapToDouble(d -> d.getHwyMPG().doubleValue())
					.sum()/carFeatureList.size();
			averageMPGPerYear.put(year, BigDecimal.valueOf(averageMPGForYear).setScale(2,RoundingMode.HALF_UP));
			}
			catch(NumberFormatException e)
			{
				e.printStackTrace();
			}
		}		
		return averageMPGPerYear;		
	}
	
	
}
