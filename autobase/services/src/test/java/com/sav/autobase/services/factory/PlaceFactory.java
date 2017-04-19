package com.sav.autobase.services.factory;

import java.util.ArrayList;
import java.util.Random;

import com.sav.autobase.datamodel.Place;

public class PlaceFactory implements IFactory<Place>{

	Place newPlace1 = new Place();
	Place newPlace2 = new Place();
	Place newPlace3 = new Place();
	ArrayList<Place> places = new ArrayList<Place>();
	
	public PlaceFactory() {
		newPlace1.setPlaceStart("Minsk");
		newPlace1.setPlaceEnd("Gomel");
		newPlace1.setDistance(251);
		
		newPlace2.setPlaceStart("Gomel");
		newPlace2.setPlaceEnd("Minsk");
		newPlace2.setDistance(251);
		
		newPlace3.setPlaceStart("Brest");
		newPlace3.setPlaceEnd("Gomel");
		newPlace3.setDistance(134);
		
		places.add(newPlace1);
		places.add(newPlace2);
		places.add(newPlace3);
	}
	
	public Place create() {
		Random r = new Random();
		return places.get(r.nextInt(places.size()));
	}
	
	

}
