package by.htp.speq.logic;

import java.io.FileNotFoundException;

import by.htp.speq.station.AvailableEquipment;
import by.htp.speq.station.RentedEquipment;

public interface StationLogic {

	RentedEquipment readRentedEquipment() throws FileNotFoundException;	
	AvailableEquipment readAvailableEquipment() throws FileNotFoundException;
	void writeRentedEquipment(RentedEquipment rentedEquipment) throws FileNotFoundException;	
	void writeAvailableEquipment(AvailableEquipment availableEquipment) throws FileNotFoundException;
	void addIncome(double income);
	void addFines(double fine);
	public double income();
	public double fine();
	
}
