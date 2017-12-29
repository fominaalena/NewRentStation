package by.htp.speq.station;

import java.util.ArrayList;
import java.util.List;

import by.htp.speq.entity.RentUnit;

public class RentedEquipment {

	private List<RentUnit> units = new ArrayList<RentUnit>();

	public RentedEquipment() {
		
	}
	
	public RentedEquipment(List<RentUnit> units) {
		super();
		this.units = units;
	}

	public List<RentUnit> getUnits() {
		return units;
	}

	public void addRentUnit(RentUnit rentUnit) {
		units.add(rentUnit);
	}

	public void setUnits(List<RentUnit> units) {
		this.units = units;
	}
	
	
}
